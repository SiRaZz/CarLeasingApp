package DanskeBank.repository;

import DanskeBank.persistance.LeasingApplicationRules;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface LeasingApplicationRulesRepository extends JpaRepository<LeasingApplicationRules, Long> {

     @Query("SELECT r FROM LeasingApplicationRules r WHERE r.validTo is null and r.ruleName = ?1")
     LeasingApplicationRules findByRuleName(String ruleName);



}
