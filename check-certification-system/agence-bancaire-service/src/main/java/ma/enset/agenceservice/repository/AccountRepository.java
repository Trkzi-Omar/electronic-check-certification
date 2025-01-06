package ma.enset.agenceservice.repository;

import ma.enset.agenceservice.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, String> {
    Account findByClientReference(String clientReference);
} 