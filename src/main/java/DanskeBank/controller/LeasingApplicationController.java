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
public class LeasingApplicationController {

    @Autowired
    LeasingApplicationService leasingApplicationService;

    @Autowired
    LeasingApplicationRulesService rulesService;

    @PostMapping("/submit")
    public ApplicationResponse submitLeasingApplication(@RequestBody LeasingApplicationDetails details) {
        return leasingApplicationService.submit(details);
    }

    @GetMapping("/getStatus/{personCode}")
    public List<LeasingApplicationDetails> getApplicationStatusByPersonCode(@PathVariable String personCode) throws PersonNotFoundException, LeasingApplicationFoundException {
             return leasingApplicationService.getApplicationStatusByPersonCode(personCode);
    }

    @PostMapping("/updateRule")
    public ResponseEntity<?> updateLeasingApplicationRule(@RequestBody LeasingApplicationRule rule) throws RuleNotFoundException {
        rulesService.updateRule(rule);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
