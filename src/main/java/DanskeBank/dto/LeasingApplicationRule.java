package DanskeBank.dto;

import DanskeBank.enums.LeasingApplicationRuleType;
import lombok.Data;

import java.util.Date;

@Data
public class LeasingApplicationRule {

    private Long id;

    private String ruleName;

    private LeasingApplicationRuleType leasingApplicationRuleType;

    private String value;

    private Date validTo;

    public LeasingApplicationRule(String ruleName, LeasingApplicationRuleType leasingApplicationRuleType, String value, Date validTo) {
        this.ruleName = ruleName;
        this.leasingApplicationRuleType = leasingApplicationRuleType;
        this.value = value;
        this.validTo = validTo;
    }
}
