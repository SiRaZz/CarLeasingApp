package DanskeBank.dto;

import DanskeBank.enums.LeasingApplicationRuleType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LeasingApplicationRule {

    private Long id;

    private String ruleName;

    private LeasingApplicationRuleType leasingApplicationRuleType;

    private String value;

    private Date validTo;
}
