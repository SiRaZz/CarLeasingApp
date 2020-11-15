package DanskeBank.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VehicleDetails {

    private Long id;

    private String vinNumber;

    private String manufacturer;

    private String model;

    private Date productionDate;

    private double carPrice;

    private boolean newCar;

    private Long enginePower;


}
