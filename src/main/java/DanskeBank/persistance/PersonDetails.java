package DanskeBank.persistance;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "PERSON_DETAILS")
public class PersonDetails {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "Id")
    private Long id;

    @Column(name = "PERSON_CODE")
    private String personCode;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "WORK_PLACE")
    private String workPlace;

    @Column(name = "MONTHLY_INCOME")
    private double monthlyIncome;

}
