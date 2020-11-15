package DanskeBank.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PersonDetails {

    private Long id;

    private String personCode;

    private String firstName;

    private String lastName;

    private String workPlace;

    private double monthlyIncome;

}
