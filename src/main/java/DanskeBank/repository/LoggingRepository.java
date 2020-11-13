package DanskeBank.repository;

import DanskeBank.persistance.LogJpa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoggingRepository extends JpaRepository<LogJpa, Long> {
}
