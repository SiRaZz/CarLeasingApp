package DanskeBank.controller;

import DanskeBank.dto.ApplicationResponse;
import DanskeBank.dto.LeasingApplicationDetails;
import DanskeBank.dto.LeasingApplicationRule;
import DanskeBank.exception.RuleNotFoundException;
import DanskeBank.service.LeasingApplicationRulesService;
import DanskeBank.service.LeasingApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rules")
public class LeasingApplicationRulesController {

    @Autowired
    LeasingApplicationRulesService rulesService;

    @PostMapping("/updateRule")
    public LeasingApplicationRule updateLeasingApplicationRule(@RequestBody LeasingApplicationRule rule) throws RuleNotFoundException {
        return rulesService.updateRule(rule);
    }

    @PostMapping("/save")
    public ResponseEntity<?> submitLeasingApplication(@RequestBody LeasingApplicationRule details) {
        rulesService.saveRule(details);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/deleteRule/{ruleName}")
    public ResponseEntity<?> updateLeasingApplicationRule(@PathVariable String ruleName) throws RuleNotFoundException {
        rulesService.deleteRuleByName(ruleName);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
