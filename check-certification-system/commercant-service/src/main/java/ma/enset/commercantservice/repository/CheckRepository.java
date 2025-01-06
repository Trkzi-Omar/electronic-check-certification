package ma.enset.commercantservice.repository;

import ma.enset.commercantservice.domain.Check;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CheckRepository extends JpaRepository<Check, Long> {
    Check findByCheckNumber(String checkNumber);
} 