package DanskeBank.service;

import DanskeBank.dto.LeasingApplicationDetails;
import DanskeBank.dto.LeasingApplicationRule;
import DanskeBank.enums.LeasingApplicationStatusEnum;
import DanskeBank.persistance.LeasingApplicationDetailsJpa;
import DanskeBank.persistance.PersonDetails;
import DanskeBank.repository.LeasingApplicationRepository;
import DanskeBank.repository.PersonDetailsRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LeasingApplicationServiceImpl implements LeasingApplicationService {

    @Autowired
    private LeasingApplicationRepository leasingApplicationRepository;
    @Autowired
    private PersonDetailsRepository personDetailsRepository;

    @Autowired
    private LeasingApplicationRulesService rulesService;

    @Autowired
    private ModelMapper modelMapper;

    public LeasingApplicationServiceImpl() {
    }

    @Override
    public LeasingApplicationDetails submit(LeasingApplicationDetails applicationDetails) {
        LeasingApplicationRule rule = rulesService.getRule("minimumIncome");
        if(applicationDetails.getPersonDetails().getMonthlyIncome() + applicationDetails.getCoApplicantDetails().getMonthlyIncome() >= Double.parseDouble(rule.getValue())) {
            applicationDetails.setStatus(LeasingApplicationStatusEnum.APPROVED);
        } else {
            applicationDetails.setStatus(LeasingApplicationStatusEnum.REJECTED);
        }
        LeasingApplicationDetailsJpa details = modelMapper.map(applicationDetails, LeasingApplicationDetailsJpa.class);
        leasingApplicationRepository.save(details);
        return applicationDetails;
    }

    @Override
    public LeasingApplicationDetails getApplicationStatusByPersonCode(String personCode) {
       LeasingApplicationDetails details = modelMapper.map(leasingApplicationRepository.findLeasingApplicationDetailsJpaByPersonDetailsId(personDetailsRepository.findPersonDetailsJpaByPersonCode(personCode).getId()), LeasingApplicationDetails.class);
       return details;

    };
}
