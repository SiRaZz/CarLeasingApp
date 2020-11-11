package DanskeBank.repository;

import DanskeBank.persistance.LeasingApplicationDetailsJpa;
import DanskeBank.persistance.PersonDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonDetailsRepository extends JpaRepository<PersonDetails, Long> {

    PersonDetails findPersonDetailsJpaByPersonCode(String personCode);

}
