package DanskeBank.carLeasingApp;

import DanskeBank.CarLeasingAppApplication;
import DanskeBank.component.LoggingComponent;
import DanskeBank.dto.LeasingApplicationDetails;
import DanskeBank.dto.LeasingApplicationRule;
import DanskeBank.dto.PersonDetails;
import DanskeBank.dto.VehicleDetails;
import DanskeBank.enums.LeasingApplicationRuleType;
import DanskeBank.exception.LeasingApplicationFoundException;
import DanskeBank.exception.PersonNotFoundException;
import DanskeBank.exception.RuleNotFoundException;
import DanskeBank.repository.LeasingApplicationRepository;
import DanskeBank.repository.PersonDetailsRepository;
import DanskeBank.service.LeasingApplicationRulesService;
import DanskeBank.service.LeasingApplicationService;
import DanskeBank.service.LeasingApplicationServiceImpl;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CarLeasingAppApplication.class)
public class LeasingApplicationServiceTest {


    @TestConfiguration
    static class LeasingApplicationServiceImplTestContextConfiguration {

        @Bean
        public LeasingApplicationService leasingApplicationService() {
            return new LeasingApplicationServiceImpl();
        }
    }

    @Autowired
    private LeasingApplicationService leasingApplicationService;

    @Autowired
    private LeasingApplicationRulesService leasingApplicationRulesService;

    @MockBean
    private LeasingApplicationRepository leasingApplicationRepository;

    @Autowired
    private PersonDetailsRepository personDetailsRepository;

    @MockBean
    private LoggingComponent loggingComponent;

    @Autowired
    ModelMapper modelMapper;

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Test
    public void should_throw_exception_saving_application() {


        LeasingApplicationDetails jpa = createTestData();
        leasingApplicationRulesService.deleteRuleByName("minimumIncome");
        exception.expect(RuleNotFoundException.class);
        leasingApplicationService.submit(jpa);

    }

    @Test
    public void should_save_application() {
        LeasingApplicationDetails jpa = createTestData();
        Assert.assertNotNull(leasingApplicationService.submit(jpa));

    }

    @Test
    public void should_not_save_application() {
        LeasingApplicationDetails jpa = createTestDataBad() ;
        exception.expect(IllegalArgumentException.class);
        Assert.assertNotNull(leasingApplicationService.submit(jpa));

    }

    @Test
    public void should_get_application_status() throws LeasingApplicationFoundException, PersonNotFoundException {
        LeasingApplicationDetails jpa = createTestData();
        LeasingApplicationRule rule1 = LeasingApplicationRule.builder().ruleName("minimumIncome")
                .leasingApplicationRuleType(LeasingApplicationRuleType.String).value("100").validTo(null).build();
        leasingApplicationRulesService.saveRule(rule1);
        leasingApplicationService.submit(jpa);
        Assert.assertNotNull(leasingApplicationService.getApplicationStatusByPersonCode("39806101355"));

    }

    @Test
    public void should_throw_exception_PersonNotFoundException_get_application_status() throws LeasingApplicationFoundException, PersonNotFoundException {
        LeasingApplicationDetails jpa = createTestData() ;
        leasingApplicationService.submit(jpa);
        exception.expect(PersonNotFoundException.class);
        Assert.assertNull(leasingApplicationService.getApplicationStatusByPersonCode("1"));

    }


    private LeasingApplicationDetails createTestData() {

        var vehicleDetails = VehicleDetails.builder()
                .carPrice(10000).enginePower(100L)
                .manufacturer("BMW").model("630")
                .newCar(false).productionDate(new Date()).vinNumber("123456789").build();

        var personDetailsJpa = PersonDetails.builder()
                .firstName("Jonas").lastName("Jonaitis")
                .monthlyIncome(1500).personCode("39806101355").workPlace("UAB Lidl").build();

        var coApplicantDetails = PersonDetails.builder()
                .firstName("Petras").lastName("Petraitis")
                .monthlyIncome(1000).personCode("35806101355").workPlace("UAB Maxima").build();

        var detail = LeasingApplicationDetails.builder()
                .leasingPeriod(36L).interestRate(3.6)
                .initialPayment(1000).vehicleDetails(vehicleDetails).personDetails(personDetailsJpa).coApplicantDetails(coApplicantDetails).build();

        return detail;
    }

    private LeasingApplicationDetails createTestDataBad() {

        var vehicleDetails = VehicleDetails.builder()
                .carPrice(10000).enginePower(100L)
                .manufacturer("BMW").model("630")
                .newCar(false).productionDate(new Date()).vinNumber("123456789").build();

        var personDetailsJpa = PersonDetails.builder()
                .firstName("Jonas").lastName("Jonaitis")
                .monthlyIncome(1500).personCode("39806101355").workPlace("UAB Lidl").build();

        var coApplicantDetails = PersonDetails.builder()
                .firstName("Petras").lastName("Petraitis")
                .monthlyIncome(1000).personCode("35806101355").workPlace("UAB Maxima").build();

        var detail = LeasingApplicationDetails.builder()
                .leasingPeriod(36L).interestRate(3.6)
                .initialPayment(1000).vehicleDetails(vehicleDetails).personDetails(null).coApplicantDetails(coApplicantDetails).build();

        return detail;
    }
}
