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
public class LeasingApplicationRules {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "Id")
    private Long id;

    @Column(name = "RULE_NAME")
    private String ruleName;

    @Column(name = "RULE_TYPE")
    @Enumerated(EnumType.STRING)
    private LeasingApplicationRuleType leasingApplicationRule;

    @Column(name = "VALUE")
    private String value;

    @Column(name = "VALID_TO")
    private Date validTo;
}
