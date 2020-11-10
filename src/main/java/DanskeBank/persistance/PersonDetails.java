package DanskeBank.persistance;

import javax.persistence.*;

@Entity
@Table(name = "PERSON_DETAILS")
public class PersonDetails {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "Id")
    private Long id;

    @Column(name = "MONTHLY_INCOME")
    private double monthlyIncome;

    private PersonDetails coApplicantPerson;

    public PersonDetails() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getMonthlyIncome() {
        return monthlyIncome;
    }

    public void setMonthlyIncome(double monthlyIncome) {
        this.monthlyIncome = monthlyIncome;
    }

    public PersonDetails getCoApplicantPerson() {
        return coApplicantPerson;
    }

    public void setCoApplicantPerson(PersonDetails coApplicantPerson) {
        this.coApplicantPerson = coApplicantPerson;
    }
}
