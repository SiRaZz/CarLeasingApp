package DanskeBank.persistance;

import DanskeBank.enums.LeasingApplicationRuleType;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Getter
@Setter
@Table(name = "LEASING_APPLICATION_RULES")
public class LeasingApplicationRulesJpa {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "Id")
    private Long id;

    @Column(name = "RULE_NAME")
    private String ruleName;

    @Column(name = "RULE_TYPE")
    @Enumerated(EnumType.STRING)
    private LeasingApplicationRuleType leasingApplicationRuleType;

    @Column(name = "VALUE")
    private String value;

    @Column(name = "VALID_TO")
    private Date validTo;

    @Column(name = "UPDATE_DATE")
    private Date updateDate;

    public LeasingApplicationRulesJpa(String ruleName, LeasingApplicationRuleType leasingApplicationRuleType, String value, Date validTo, Date updateDate) {
        this.ruleName = ruleName;
        this.leasingApplicationRuleType = leasingApplicationRuleType;
        this.value = value;
        this.validTo = validTo;
        this.updateDate = updateDate;
    }
}
