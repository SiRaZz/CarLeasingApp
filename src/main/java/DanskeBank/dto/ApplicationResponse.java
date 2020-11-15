package DanskeBank.dto;

import DanskeBank.enums.LeasingApplicationStatusEnum;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ApplicationResponse {

    private final VehicleDetails vehicleDetails;

    private final LeasingApplicationStatusEnum status;
}
