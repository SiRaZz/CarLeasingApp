package DanskeBank.carLeasingApp;

import DanskeBank.CarLeasingAppApplication;
import DanskeBank.component.LoggingComponent;
import DanskeBank.dto.ApplicationResponse;
import DanskeBank.dto.LeasingApplicationDetails;
import DanskeBank.dto.PersonDetails;
import DanskeBank.dto.VehicleDetails;
import DanskeBank.exception.RuleNotFoundException;
import DanskeBank.repository.LeasingApplicationRepository;
import DanskeBank.repository.PersonDetailsRepository;
import DanskeBank.service.LeasingApplicationRulesService;
import DanskeBank.service.LeasingApplicationService;
import DanskeBank.service.LeasingApplicationServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
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

    @MockBean
    private LeasingApplicationRulesService leasingApplicationRulesService;

    @MockBean
    private LeasingApplicationRepository leasingApplicationRepository;

    @MockBean
    private PersonDetailsRepository personDetailsRepository;

    @MockBean
    private LoggingComponent loggingComponent;

    @MockBean
    ModelMapper modelMapper;

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Test
    public void should_throw_exception_saving_application() {
        LeasingApplicationDetails jpa = createTestData();

        exception.expect(RuleNotFoundException.class);
        leasingApplicationService.submit(jpa);

    }

    private LeasingApplicationDetails createTestData() {
        LeasingApplicationDetails detail = new LeasingApplicationDetails();

        VehicleDetails vehicleDetails = new VehicleDetails();
        vehicleDetails.setCarPrice(10000);
        vehicleDetails.setEnginePower(100L);
        vehicleDetails.setManufacturer("BMW");
        vehicleDetails.setModel("630");
        vehicleDetails.setNewCar(false);
        vehicleDetails.setProductionDate(new Date());
        vehicleDetails.setVinNumber("123456789");

        PersonDetails personDetailsJpa = new PersonDetails();
        personDetailsJpa.setFirstName("Jonas");
        personDetailsJpa.setLastName("Jonaitis");
        personDetailsJpa.setMonthlyIncome(1500);
        personDetailsJpa.setPersonCode("39806101355");
        personDetailsJpa.setWorkPlace("UAB Lidl");

        PersonDetails coApplicantDetails = new PersonDetails();
        coApplicantDetails.setFirstName("Petras");
        coApplicantDetails.setLastName("Petraitis");
        coApplicantDetails.setMonthlyIncome(1000);
        coApplicantDetails.setPersonCode("35806101355");
        coApplicantDetails.setWorkPlace("UAB Maxima");

        detail.setLeasingPeriod(36L);
        detail.setInterestRate(3.6);
        detail.setInitialPayment(1000);
        detail.setVehicleDetails(vehicleDetails);
        detail.setPersonDetails(personDetailsJpa);
        detail.setCoApplicantDetails(coApplicantDetails);

        return detail;
    }
}
