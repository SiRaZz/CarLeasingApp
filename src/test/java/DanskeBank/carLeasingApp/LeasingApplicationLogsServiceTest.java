package DanskeBank.carLeasingApp;

import DanskeBank.CarLeasingAppApplication;
import DanskeBank.dto.LogDetails;
import DanskeBank.persistance.LogJpa;
import DanskeBank.repository.LeasingApplicationRulesRepository;
import DanskeBank.repository.LoggingRepository;
import DanskeBank.service.LeasingApplicationLogService;
import DanskeBank.service.LeasingApplicationLogServiceImpl;
import DanskeBank.service.LeasingApplicationRulesService;
import DanskeBank.service.LeasingApplicationRulesServiceImpl;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CarLeasingAppApplication.class)
public class LeasingApplicationLogsServiceTest {

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @TestConfiguration
    static class LeasingApplicationLogServiceImplTestContextConfiguration {

        @Bean
        public LeasingApplicationLogService leasingApplicationLogService() {
            return new LeasingApplicationLogServiceImpl();
        }
    }

    @Autowired
    private LeasingApplicationLogService leasingApplicationLogService;

    @MockBean
    private LoggingRepository loggingRepository;


    @Test
    public void should_get_logs() {
        loggingRepository.save(initLogs());
        Assert.assertNotNull(leasingApplicationLogService.getLogs());

    }

    private LogJpa initLogs() {
        return LogJpa.builder().id(1L).method("POST").requestDate(new Date()).responseStatus(200).url("url").build();

    }

}
