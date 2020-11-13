package DanskeBank.service;

import DanskeBank.dto.ApplicationResponse;
import DanskeBank.dto.LeasingApplicationDetails;
import DanskeBank.exception.LeasingApplicationFoundException;
import DanskeBank.exception.PersonNotFoundException;
import org.springframework.boot.actuate.trace.http.HttpTrace;

import java.util.List;

public interface LeasingApplicationService {

    ApplicationResponse submit(LeasingApplicationDetails details);

    List<LeasingApplicationDetails> getApplicationStatusByPersonCode(String personCode) throws PersonNotFoundException, LeasingApplicationFoundException;

    List<HttpTrace> getLog();


}
