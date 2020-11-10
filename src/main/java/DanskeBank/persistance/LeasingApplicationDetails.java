package DanskeBank.persistance;

import javax.persistence.*;

@Entity
@Table(name = "LEASING_APPLICATION_DETAILS")
public class LeasingApplicationDetails {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
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
    private String status;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getLeasingPeriod() {
        return leasingPeriod;
    }

    public void setLeasingPeriod(Long leasingPeriod) {
        this.leasingPeriod = leasingPeriod;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    public double getInitialPayment() {
        return initialPayment;
    }

    public void setInitialPayment(double initialPayment) {
        this.initialPayment = initialPayment;
    }

    public double getMonthlyPaymentAmount() {
        return monthlyPaymentAmount;
    }

    public void setMonthlyPaymentAmount(double monthlyPaymentAmount) {
        this.monthlyPaymentAmount = monthlyPaymentAmount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
