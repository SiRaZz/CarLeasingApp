package DanskeBank.persistance;

import DanskeBank.enums.LeasingApplicationStatusEnum;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Data
@Entity
@Getter
@Setter
@Table(name = "LEASING_APPLICATION_DETAILS")
public class LeasingApplicationDetailsJpa {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "Id")
    private Long id;

    @Column(name = "LEASING_PERIOD")
    private Long leasingPeriod;

    @Column(name = "INTEREST_RATE")
    private double interestRate;

    @Column(name = "INITIAL_PAYMENT")
    private double initialPayment;

    @Column(name = "MONTHLY_PAYMENT_AMOUNT")
    private double monthlyPaymentAmount;

    @Column(name = "STATUS")
    @Enumerated(EnumType.STRING)
    private LeasingApplicationStatusEnum status;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "vehicle_details_id", referencedColumnName = "id")
    private VehicleDetailsJpa vehicleDetails;

    @Column(name = "PERSON_DETAILS_ID")
    private Long personDetailsId;

    @Column(name = "CO_APPLICANT_DETAILS_ID")
    private Long coApplicantDetailsId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PERSON_DETAILS_ID", insertable = false, updatable = false)
    private PersonDetailsJpa personDetails;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CO_APPLICANT_DETAILS_ID", insertable = false, updatable = false)
    private PersonDetailsJpa coApplicantDetails;
}
