package DanskeBank.dto;

import DanskeBank.enums.LeasingApplicationStatusEnum;
import lombok.Data;

@Data
public class LeasingApplicationDetails {

    private Long id;

    private Long leasingPeriod;

    private double interestRate;

    private double initialPayment;

    private double monthlyPaymentAmount;

    private LeasingApplicationStatusEnum status;

    private VehicleDetails vehicleDetails;

    private PersonDetails personDetails;

    private PersonDetails coApplicantDetails;

}
