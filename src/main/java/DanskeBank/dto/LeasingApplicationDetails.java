package DanskeBank.dto;

import DanskeBank.enums.LeasingApplicationStatusEnum;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LeasingApplicationDetails {

    private Long id;

    private Long leasingPeriod;

    private double interestRate;

    private double initialPayment;

    private LeasingApplicationStatusEnum status;

    private VehicleDetails vehicleDetails;

    private PersonDetails personDetails;

    private PersonDetails coApplicantDetails;

}
