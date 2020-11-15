package DanskeBank.persistance;

import DanskeBank.enums.LeasingApplicationRuleType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
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


}
