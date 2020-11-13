package DanskeBank.carLeasingApp;

import DanskeBank.dto.ApplicationResponse;
import DanskeBank.dto.LeasingApplicationDetails;
import DanskeBank.dto.PersonDetails;
import DanskeBank.dto.VehicleDetails;
import DanskeBank.repository.LeasingApplicationRepository;
import DanskeBank.service.LeasingApplicationService;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;

@ExtendWith(MockitoExtension.class)
public class LeasingApplicationServiceTest {


    @Mock
    LeasingApplicationService leasingApplicationService;

    @InjectMocks
    LeasingApplicationRepository leasingApplicationRepository;


    @Test
    public void should_save_application() {

        LeasingApplicationDetails jpa = createTestData();

        //Idk why this not working
        ApplicationResponse response = leasingApplicationService.submit(jpa);

        Assertions.assertThat(response).isNotNull();


    }

    private LeasingApplicationDetails createTestData() {
        LeasingApplicationDetails detail = new LeasingApplicationDetails();

        VehicleDetails vehicleDetails = new VehicleDetails();
        vehicleDetails.setCarPrice(10000);
        vehicleDetails.setEnginePower(100l);
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

        detail.setLeasingPeriod(36l);
        detail.setInterestRate(3.6);
        detail.setInitialPayment(1000);
        /*detail.setVehicleDetails(vehicleDetails);*/
        detail.setPersonDetails(personDetailsJpa);
        detail.setCoApplicantDetails(coApplicantDetails);

        return detail;
    }
}
