package DanskeBank.dto;

import DanskeBank.enums.LeasingApplicationRuleType;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.Date;

@Data
public class LeasingApplicationRule {

    private Long id;

    private String ruleName;

    private LeasingApplicationRuleType leasingApplicationRule;

    private String value;

    private Date validTo;
}
