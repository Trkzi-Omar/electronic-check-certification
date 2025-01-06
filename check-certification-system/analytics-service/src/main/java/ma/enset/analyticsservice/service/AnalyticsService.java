package ma.enset.analyticsservice.service;

import ma.enset.analyticsservice.domain.OperationEvent;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public interface AnalyticsService {
    List<OperationEvent> getAllEvents();
    List<OperationEvent> getEventsByTimeRange(LocalDateTime start, LocalDateTime end);
    List<OperationEvent> getEventsByType(String operationType);
    Map<String, Double> getOperationStatsByType();
    Map<String, Double> getOperationStatsByAccount(String accountNumber);
    Double getTotalAmountByOperationType(String operationType);
} 