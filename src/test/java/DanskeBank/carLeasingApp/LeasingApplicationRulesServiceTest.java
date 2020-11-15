package DanskeBank.carLeasingApp;

import DanskeBank.CarLeasingAppApplication;
import DanskeBank.dto.LeasingApplicationRule;
import DanskeBank.enums.LeasingApplicationRuleType;
import DanskeBank.exception.RuleNotFoundException;
import DanskeBank.exception.SameRuleFoundException;
import DanskeBank.persistance.LeasingApplicationRulesJpa;
import DanskeBank.repository.LeasingApplicationRulesRepository;
import DanskeBank.service.LeasingApplicationRulesService;
import DanskeBank.service.LeasingApplicationRulesServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = CarLeasingAppApplication.class)
public class LeasingApplicationRulesServiceTest {

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @TestConfiguration
    static class LeasingApplicationServiceImplTestContextConfiguration {

        @Bean
        public LeasingApplicationRulesService leasingApplicationRulesService() {
            return new LeasingApplicationRulesServiceImpl();
        }
    }

    @Autowired
    private LeasingApplicationRulesService rulesService;

    @MockBean
    private LeasingApplicationRulesRepository rulesRepository;

    @MockBean
    ModelMapper modelMapper;

    @Test
    public void should_not_find_rule_by_name() {
        String name = "randomName";
        Assert.assertNull(rulesService.getRule(name));
    }

    @Test
    public void should_delete_rule_by_name() {
        LeasingApplicationRule rule1 = LeasingApplicationRule.builder().ruleName("randomName")
                .leasingApplicationRuleType(LeasingApplicationRuleType.String).value("100").validTo(null).build();
        rulesService.saveRule(rule1);
        String name = "randomName";
        rulesService.deleteRuleByName(name);
    }


    @Test
    public void should_throw_exception_updating_rule() {
        LeasingApplicationRule rule1 = LeasingApplicationRule.builder().ruleName("randomName")
                .leasingApplicationRuleType(LeasingApplicationRuleType.String).value("100").validTo(null).build();
        exception.expect(RuleNotFoundException.class);
        rulesService.updateRule(rule1);

    }

    @Test
    public void should_save_rule() {
        LeasingApplicationRule rule1 = LeasingApplicationRule.builder().ruleName("minimumIncome1")
                .leasingApplicationRuleType(LeasingApplicationRuleType.String).value("100").validTo(null).build();
        rulesService.saveRule(rule1);

        Assertions.assertThat(rule1).hasFieldOrPropertyWithValue("ruleName", "minimumIncome1");
        Assertions.assertThat(rule1).hasFieldOrPropertyWithValue("leasingApplicationRuleType", LeasingApplicationRuleType.String);
        Assertions.assertThat(rule1).hasFieldOrPropertyWithValue("value", "100");
        Assertions.assertThat(rule1).hasFieldOrPropertyWithValue("validTo", null);
    }
}
