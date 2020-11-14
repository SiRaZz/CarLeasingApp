package DanskeBank.carLeasingApp;

import DanskeBank.enums.LeasingApplicationRuleType;
import DanskeBank.exception.RuleNotFoundException;
import DanskeBank.persistance.LeasingApplicationRulesJpa;
import DanskeBank.repository.LeasingApplicationRulesRepository;
import org.assertj.core.api.Assertions;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class LeasingApplicationRulesRepositoryTest {

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    LeasingApplicationRulesRepository rulesRepository;

    @Test
    public void should_save_rule() {

        LeasingApplicationRulesJpa jpa = rulesRepository.save(LeasingApplicationRulesJpa.builder().ruleName("minimumIncome1")
                        .leasingApplicationRuleType(LeasingApplicationRuleType.String).value("100").updateDate(null).validTo(null).build());


        Assertions.assertThat(jpa).hasFieldOrPropertyWithValue("ruleName", "minimumIncome1");
        Assertions.assertThat(jpa).hasFieldOrPropertyWithValue("leasingApplicationRuleType", LeasingApplicationRuleType.String);
        Assertions.assertThat(jpa).hasFieldOrPropertyWithValue("value", "100");
        Assertions.assertThat(jpa).hasFieldOrPropertyWithValue("validTo", null);
        Assertions.assertThat(jpa).hasFieldOrPropertyWithValue("updateDate", null);
    }

    @Test
    public void should_find_tutorial_rule_by_name() {
        LeasingApplicationRulesJpa rule1 = LeasingApplicationRulesJpa.builder().ruleName("minimumIncome1")
                .leasingApplicationRuleType(LeasingApplicationRuleType.String).value("100").updateDate(null).validTo(null).build();
        entityManager.persist(rule1);

        LeasingApplicationRulesJpa foundRule = rulesRepository.findByRuleName(rule1.getRuleName());

        Assertions.assertThat(foundRule).isEqualTo(rule1);
    }

    @Test
    public void should_not_find_rule_by_name_ant_throw() {
        LeasingApplicationRulesJpa rule1 = LeasingApplicationRulesJpa.builder().ruleName("minimumIncome1")
                .leasingApplicationRuleType(LeasingApplicationRuleType.String).value("100").updateDate(null).validTo(null).build();
        entityManager.persist(rule1);

        exception.expect(RuleNotFoundException.class);
        LeasingApplicationRulesJpa foundRule = rulesRepository.findByRuleName("randomRule");


    }

    @Test
    public void should_update_rule_by_name() {
        LeasingApplicationRulesJpa rule1 = LeasingApplicationRulesJpa.builder().ruleName("minimumIncome1")
                .leasingApplicationRuleType(LeasingApplicationRuleType.String).value("100").updateDate(null).validTo(null).build();
        entityManager.persist(rule1);

        LeasingApplicationRulesJpa updatedRule1 = LeasingApplicationRulesJpa.builder().ruleName("minimumIncome1")
                .leasingApplicationRuleType(LeasingApplicationRuleType.String).value("1000").updateDate(null).validTo(null).build();

        LeasingApplicationRulesJpa foundRule = rulesRepository.findByRuleName(rule1.getRuleName());
        foundRule.setValue(updatedRule1.getValue());
        foundRule.setUpdateDate(updatedRule1.getUpdateDate());
        rulesRepository.save(foundRule);

        LeasingApplicationRulesJpa checkRule = rulesRepository.findByRuleName(rule1.getRuleName());

        Assertions.assertThat(checkRule.getId()).isEqualTo(rule1.getId());
        Assertions.assertThat(checkRule.getRuleName()).isEqualTo(rule1.getRuleName());
        Assertions.assertThat(checkRule.getValue()).isEqualTo(rule1.getValue());
        Assertions.assertThat(checkRule.getUpdateDate()).isEqualTo(rule1.getUpdateDate());
    }


}
