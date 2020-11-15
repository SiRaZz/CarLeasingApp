package DanskeBank.dto;

import DanskeBank.enums.LeasingApplicationStatusEnum;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LeasingApplicationDetails {

    private final Long id;

    private final Long leasingPeriod;

    private final double interestRate;

    private final double initialPayment;

    private final LeasingApplicationStatusEnum status;

    private final VehicleDetails vehicleDetails;

    private final PersonDetails personDetails;

    private final PersonDetails coApplicantDetails;

}
