package DanskeBank.service;

import DanskeBank.dto.LeasingApplicationRule;
import DanskeBank.exception.LeasingApplicationFoundException;
import DanskeBank.exception.RuleNotFoundException;

public interface LeasingApplicationRulesService {

    LeasingApplicationRule getRule(String ruleName);

    void updateRule(LeasingApplicationRule rule) throws RuleNotFoundException;

}
