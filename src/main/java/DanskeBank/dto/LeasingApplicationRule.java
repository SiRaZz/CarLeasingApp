package DanskeBank.dto;

import DanskeBank.enums.LeasingApplicationRuleType;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class LeasingApplicationRule {

    private Long id;

    private String ruleName;

    private LeasingApplicationRuleType leasingApplicationRuleType;

    private String value;

    private Date validTo;
}
