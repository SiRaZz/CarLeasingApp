package DanskeBank.dto;

import lombok.Data;

@Data
public class PersonDetails {

    private Long id;

    private String personCode;

    private String firstName;

    private String lastName;

    private String workPlace;

    private double monthlyIncome;

}
