package DanskeBank.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PersonDetails {

    private final Long id;

    private final String personCode;

    private final String firstName;

    private final String lastName;

    private final String workPlace;

    private final double monthlyIncome;

}
