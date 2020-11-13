package DanskeBank.dto;

import DanskeBank.enums.LeasingApplicationStatusEnum;
import lombok.Data;

@Data
public class ApplicationResponse {

    private VehicleDetails vehicleDetails;

    private LeasingApplicationStatusEnum status;

    public ApplicationResponse(VehicleDetails vehicleDetails, LeasingApplicationStatusEnum status) {
        this.vehicleDetails = vehicleDetails;
        this.status = status;
    }
}
