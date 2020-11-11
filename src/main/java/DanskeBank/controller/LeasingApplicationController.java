package DanskeBank.controller;

import DanskeBank.dto.LeasingApplicationDetails;
import DanskeBank.service.LeasingApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class LeasingApplicationController {

    @Autowired
    LeasingApplicationService leasingApplicationService;

    @PostMapping("/submit")
    public LeasingApplicationDetails submitLeasingApplication(@RequestBody LeasingApplicationDetails details) {
        return leasingApplicationService.submit(details);
    }

    @GetMapping("/getStatus/{personCode}")
    public LeasingApplicationDetails getApplicationStatusByPersonCode(@PathVariable String personCode) {
        return leasingApplicationService.getApplicationStatusByPersonCode(personCode);
    }

}
