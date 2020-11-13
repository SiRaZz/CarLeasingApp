package DanskeBank.service;

import DanskeBank.dto.LeasingApplicationRule;
import DanskeBank.exception.RuleNotFoundException;


public interface LeasingApplicationRulesService {

    LeasingApplicationRule getRule(String ruleName);

    LeasingApplicationRule updateRule(LeasingApplicationRule rule) throws RuleNotFoundException;

}
