package ma.enset.analyticsservice.repository;

import ma.enset.analyticsservice.domain.OperationEvent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface OperationEventRepository extends JpaRepository<OperationEvent, Long> {
    List<OperationEvent> findByTimestampBetween(LocalDateTime start, LocalDateTime end);
    List<OperationEvent> findByOperationType(String operationType);
} 