package DanskeBank.repository;

import DanskeBank.persistance.LeasingApplicationRulesJpa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface LeasingApplicationRulesRepository extends JpaRepository<LeasingApplicationRulesJpa, Long> {

     @Query("SELECT r FROM LeasingApplicationRulesJpa r WHERE r.validTo is null and r.ruleName = ?1")
     LeasingApplicationRulesJpa findByRuleName(String ruleName);

     @Transactional
     @Modifying
     @Query("DELETE FROM LeasingApplicationRulesJpa r WHERE r.ruleName = ?1")
     void deleteRuleByName(String ruleName);

}
