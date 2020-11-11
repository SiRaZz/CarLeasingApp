package DanskeBank.dto;

import lombok.Data;

import java.util.Date;

@Data
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
