package DanskeBank.service;

import DanskeBank.dto.LeasingApplicationRule;
import DanskeBank.repository.LeasingApplicationRulesRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LeasingApplicationRulesServiceImpl implements LeasingApplicationRulesService {

    @Autowired
    private LeasingApplicationRulesRepository leasingApplicationRulesRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public LeasingApplicationRule getRule(String ruleName) {
        return modelMapper.map(leasingApplicationRulesRepository.findByRuleName(ruleName), LeasingApplicationRule.class);
    }
}