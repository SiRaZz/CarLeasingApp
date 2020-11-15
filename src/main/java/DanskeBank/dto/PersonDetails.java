package DanskeBank.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PersonDetails {

    private Long id;

    private String personCode;

    private String firstName;

    private String lastName;

    private String workPlace;

    private double monthlyIncome;

}
