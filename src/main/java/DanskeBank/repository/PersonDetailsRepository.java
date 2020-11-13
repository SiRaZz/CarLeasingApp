package DanskeBank.repository;

import DanskeBank.persistance.PersonDetailsJpa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonDetailsRepository extends JpaRepository<PersonDetailsJpa, Long> {

    PersonDetailsJpa findPersonDetailsJpaByPersonCode(String personCode);

}
