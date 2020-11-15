package DanskeBank.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
public class VehicleDetails {

    private final Long id;

    private final String vinNumber;

    private final String manufacturer;

    private final String model;

    private final Date productionDate;

    private final double carPrice;

    private final boolean newCar;

    private final Long enginePower;


}
