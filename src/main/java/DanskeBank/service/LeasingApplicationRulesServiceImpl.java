package DanskeBank.service;

import DanskeBank.dto.LeasingApplicationRule;
import DanskeBank.exception.LeasingApplicationFoundException;
import DanskeBank.exception.RuleNotFoundException;
import DanskeBank.persistance.LeasingApplicationRulesJpa;
import DanskeBank.repository.LeasingApplicationRulesRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Optional;

@Component
public class LeasingApplicationRulesServiceImpl implements LeasingApplicationRulesService {

    private static final Logger log = LoggerFactory.getLogger(LeasingApplicationRulesServiceImpl.class);

    @Autowired
    private LeasingApplicationRulesRepository leasingApplicationRulesRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private MessageSource messages;

    @Override
    public LeasingApplicationRule getRule(String ruleName) {
        return Optional.ofNullable(leasingApplicationRulesRepository.findByRuleName(ruleName)).map(rule -> modelMapper.map(rule, LeasingApplicationRule.class)).orElse(null);
    }

    @Override
    public void updateRule(LeasingApplicationRule rule) throws RuleNotFoundException {
        LeasingApplicationRulesJpa applicationRule = leasingApplicationRulesRepository.findByRuleName(rule.getRuleName());
        if (applicationRule != null && (!applicationRule.getValue().equals(rule.getValue()))) {
            applicationRule.setValue(rule.getValue());
            applicationRule.setUpdateDate(new Date());
            leasingApplicationRulesRepository.saveAndFlush(applicationRule);
        } else {
            log.info("Rule with name {} not found or value not changed, skipping", rule.getRuleName());
            throw new RuleNotFoundException(HttpStatus.NOT_FOUND, messages.getMessage("leasingApplication.ruleNotFound", null,null));
        }
    }
}