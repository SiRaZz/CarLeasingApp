package DanskeBank.service;

import DanskeBank.component.LoggingComponent;
import DanskeBank.dto.*;
import DanskeBank.enums.LeasingApplicationStatusEnum;
import DanskeBank.exception.LeasingApplicationFoundException;
import DanskeBank.exception.PersonNotFoundException;
import DanskeBank.exception.RuleNotFoundException;
import DanskeBank.persistance.LeasingApplicationDetailsJpa;
import DanskeBank.persistance.LeasingApplicationRulesJpa;
import DanskeBank.persistance.PersonDetailsJpa;
import DanskeBank.persistance.VehicleDetailsJpa;
import DanskeBank.repository.LeasingApplicationRepository;
import DanskeBank.repository.LeasingApplicationRulesRepository;
import DanskeBank.repository.LoggingRepository;
import DanskeBank.repository.PersonDetailsRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.internal.util.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.trace.http.HttpTrace;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class LeasingApplicationServiceImpl implements LeasingApplicationService {

    private static final Logger log = LoggerFactory.getLogger(LeasingApplicationServiceImpl.class);
    private static final DecimalFormat df = new DecimalFormat("#.##");

    @Autowired
    private LeasingApplicationRepository leasingApplicationRepository;

    @Autowired
    private LoggingComponent loggingComponent;

    @Autowired
    private LoggingRepository loggingRepository;

    @Autowired
    private PersonDetailsRepository personDetailsRepository;

    @Autowired
    private LeasingApplicationRulesRepository rulesRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private MessageSource messages;

    @Override
    public ApplicationResponse submit(LeasingApplicationDetails applicationDetails) {
        df.setRoundingMode(RoundingMode.UP);
        var rule = rulesRepository.findByRuleName("minimumIncome");
        if (rule != null) {
            validateLeasingApplication(applicationDetails);
            var details = validateDetails(applicationDetails, rule);
            details.setMonthlyPaymentAmount(Double.parseDouble(df.format((details.getVehicleDetails().getCarPrice() - details.getInitialPayment()) / details.getLeasingPeriod())));
            leasingApplicationRepository.save(details);
            log.info("Leasing application with id={} submitted", details.getId());
            return ApplicationResponse.builder().vehicleDetails(modelMapper.map(details.getVehicleDetails(), VehicleDetails.class)).status(details.getStatus()).build();
        } else {
            throw new RuleNotFoundException(HttpStatus.NOT_FOUND, messages.getMessage("leasingApplication.ruleNotFound", null, Locale.getDefault()));
        }
    }

    @Override
    public List<LeasingApplicationDetails> getApplicationStatusByPersonCode(String personCode) throws PersonNotFoundException, LeasingApplicationFoundException {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        var personDetails = personDetailsRepository.findPersonDetailsJpaByPersonCode(personCode);
        if (personDetails != null) {
            var details = leasingApplicationRepository.findLeasingApplicationDetailsJpaByPersonDetailsId(personDetails.getId());
            if (details != null) {
                return details
                        .stream()
                        .map(d -> modelMapper.map(d, LeasingApplicationDetails.class))
                        .collect(Collectors.toList());
            } else {
                throw new LeasingApplicationFoundException(HttpStatus.NOT_FOUND, messages.getMessage("leasingApplication.null", null, Locale.getDefault()));
            }
        } else {
            throw new PersonNotFoundException(HttpStatus.NOT_FOUND, messages.getMessage("leasingApplication.personNotFound", null, Locale.getDefault()));
        }
    }

    protected void validateLeasingApplication(LeasingApplicationDetails applicationDetails) {
        Assert.notNull(applicationDetails.getPersonDetails());
        Assert.notNull(applicationDetails.getCoApplicantDetails());
        Assert.notNull(applicationDetails.getVehicleDetails());
    }

    private LeasingApplicationDetailsJpa validateDetails(LeasingApplicationDetails applicationDetails, LeasingApplicationRulesJpa rule) {
        var details = LeasingApplicationDetailsJpa.builder().initialPayment(applicationDetails.getInitialPayment())
                .interestRate(applicationDetails.getInterestRate())
                .leasingPeriod(applicationDetails.getLeasingPeriod())
                .vehicleDetails(modelMapper.map(applicationDetails.getVehicleDetails(), VehicleDetailsJpa.class)).build();

        var personDetails = personDetailsRepository.findPersonDetailsJpaByPersonCode(applicationDetails.getPersonDetails().getPersonCode());
        var coApplicantDetails = personDetailsRepository.findPersonDetailsJpaByPersonCode(applicationDetails.getCoApplicantDetails().getPersonCode());
        if (applicationDetails.getPersonDetails().getMonthlyIncome() + applicationDetails.getCoApplicantDetails().getMonthlyIncome() >= Double.parseDouble(rule.getValue())) {
            details.setStatus(LeasingApplicationStatusEnum.APPROVED);
        } else {
            details.setStatus(LeasingApplicationStatusEnum.REJECTED);
        }
        details.setPersonDetailsId(Objects.requireNonNullElseGet(personDetails, () -> personDetailsRepository.save(modelMapper.map(applicationDetails.getPersonDetails(), PersonDetailsJpa.class))).getId());
        details.setCoApplicantDetailsId(Objects.requireNonNullElseGet(coApplicantDetails, () -> personDetailsRepository.save(modelMapper.map(applicationDetails.getCoApplicantDetails(), PersonDetailsJpa.class))).getId());
        return details;
    }
}
