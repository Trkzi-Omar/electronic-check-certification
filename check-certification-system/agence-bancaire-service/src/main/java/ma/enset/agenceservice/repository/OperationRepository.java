package ma.enset.agenceservice.repository;

import ma.enset.agenceservice.domain.Operation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OperationRepository extends JpaRepository<Operation, Long> {
    List<Operation> findByAccountNumber(String accountNumber);
    List<Operation> findByCheckNumber(String checkNumber);
} 