package DanskeBank.dto;

import DanskeBank.enums.LeasingApplicationStatusEnum;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ApplicationResponse {

    private VehicleDetails vehicleDetails;

    private LeasingApplicationStatusEnum status;
}
