package DanskeBank.repository;

import DanskeBank.persistance.LeasingApplicationDetailsJpa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LeasingApplicationRepository extends JpaRepository<LeasingApplicationDetailsJpa, Long> {


    LeasingApplicationDetailsJpa findLeasingApplicationDetailsJpaByPersonDetailsId(Long personId);
}
