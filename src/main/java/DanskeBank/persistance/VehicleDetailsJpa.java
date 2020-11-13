package DanskeBank.persistance;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "VEHICLE_DETAILS")
public class VehicleDetailsJpa {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "Id")
    private Long id;

    @Column(name = "VIN_NUMBER")
    private String vinNumber;

    @Column(name = "MANUFACTURER")
    private String manufacturer;

    @Column(name = "MODEL")
    private String model;

    @Column(name = "PRODUCTION_DATE")
    private Date productionDate;

    @Column(name = "CAR_PRICE")
    private double carPrice;

    @Column(name = "NEW_CAR")
    private boolean newCar;

    @Column(name = "ENGINE_POWER")
    private Long enginePower;
}
