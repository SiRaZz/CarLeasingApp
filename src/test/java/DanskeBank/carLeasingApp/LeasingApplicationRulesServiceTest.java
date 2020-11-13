package DanskeBank.carLeasingApp;

import DanskeBank.dto.LeasingApplicationRule;
import DanskeBank.enums.LeasingApplicationRuleType;
import DanskeBank.exception.RuleNotFoundException;
import DanskeBank.repository.LeasingApplicationRulesRepository;
import DanskeBank.service.LeasingApplicationRulesService;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
public class LeasingApplicationRulesServiceTest {

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Mock
    private LeasingApplicationRulesService rulesService;

    @Mock
    private LeasingApplicationRulesRepository rulesRepository;

    @Test
    public void should_not_find_rule_by_name() {
        String name = "randomName";
        Assert.assertNull(rulesService.getRule(name));
    }

    @Test
    public void should_throw_exception_updating_rule() {
        LeasingApplicationRule rule1 = new LeasingApplicationRule("randomName", LeasingApplicationRuleType.String, "100", null);
        Mockito.when(rulesService.updateRule(rule1)).thenThrow(new RuleNotFoundException(HttpStatus.NOT_FOUND, "NOT_FOUND"));

    }
}
