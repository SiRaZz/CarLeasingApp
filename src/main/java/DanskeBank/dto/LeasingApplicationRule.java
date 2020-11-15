package DanskeBank.dto;

import DanskeBank.enums.LeasingApplicationRuleType;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class LeasingApplicationRule {

    private final Long id;

    private final String ruleName;

    private final LeasingApplicationRuleType leasingApplicationRuleType;

    private final String value;

    private final Date validTo;
}
