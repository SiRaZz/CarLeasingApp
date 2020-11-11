package DanskeBank.service;

import DanskeBank.dto.LeasingApplicationDetails;

public interface LeasingApplicationService {

    LeasingApplicationDetails submit(LeasingApplicationDetails details);

    LeasingApplicationDetails getApplicationStatusByPersonCode(String personCode);


}
