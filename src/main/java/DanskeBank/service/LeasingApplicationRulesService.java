package DanskeBank.service;

import DanskeBank.dto.LeasingApplicationRule;
import DanskeBank.exception.RuleNotFoundException;


public interface LeasingApplicationRulesService {

    LeasingApplicationRule getRule(String ruleName);

    void saveRule(LeasingApplicationRule rule);

    LeasingApplicationRule updateRule(LeasingApplicationRule rule) throws RuleNotFoundException;

    void deleteRuleByName (String ruleName);

}
