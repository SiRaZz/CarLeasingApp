package DanskeBank.controller;

import DanskeBank.dto.ApplicationResponse;
import DanskeBank.dto.LeasingApplicationDetails;
import DanskeBank.dto.LeasingApplicationRule;
import DanskeBank.exception.LeasingApplicationFoundException;
import DanskeBank.exception.PersonNotFoundException;
import DanskeBank.exception.RuleNotFoundException;
import DanskeBank.service.LeasingApplicationRulesService;
import DanskeBank.service.LeasingApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.trace.http.HttpTrace;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rules")
public class LeasingApplicationRulesController {

    @Autowired
    LeasingApplicationService leasingApplicationService;

    @Autowired
    LeasingApplicationRulesService rulesService;

    @PostMapping("/updateRule")
    public LeasingApplicationRule updateLeasingApplicationRule(@RequestBody LeasingApplicationRule rule) throws RuleNotFoundException {
        return rulesService.updateRule(rule);
    }

    @PostMapping("/deleteRule/{ruleName}")
    public ResponseEntity<?> updateLeasingApplicationRule(@PathVariable String ruleName) throws RuleNotFoundException {
        rulesService.deleteRuleByName(ruleName);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
