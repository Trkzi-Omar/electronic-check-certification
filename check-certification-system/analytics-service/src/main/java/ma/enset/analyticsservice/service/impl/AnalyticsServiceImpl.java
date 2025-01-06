package ma.enset.analyticsservice.service.impl;

import lombok.RequiredArgsConstructor;
import ma.enset.analyticsservice.domain.OperationEvent;
import ma.enset.analyticsservice.repository.OperationEventRepository;
import ma.enset.analyticsservice.service.AnalyticsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class AnalyticsServiceImpl implements AnalyticsService {
    private final OperationEventRepository operationEventRepository;

    @Override
    public List<OperationEvent> getAllEvents() {
        return operationEventRepository.findAll();
    }

    @Override
    public List<OperationEvent> getEventsByTimeRange(LocalDateTime start, LocalDateTime end) {
        return operationEventRepository.findByTimestampBetween(start, end);
    }

    @Override
    public List<OperationEvent> getEventsByType(String operationType) {
        return operationEventRepository.findByOperationType(operationType);
    }

    @Override
    public Map<String, Double> getOperationStatsByType() {
        return operationEventRepository.findAll().stream()
                .collect(Collectors.groupingBy(
                        OperationEvent::getOperationType,
                        Collectors.summingDouble(OperationEvent::getAmount)
                ));
    }

    @Override
    public Map<String, Double> getOperationStatsByAccount(String accountNumber) {
        return operationEventRepository.findAll().stream()
                .filter(event -> event.getAccountNumber().equals(accountNumber))
                .collect(Collectors.groupingBy(
                        OperationEvent::getOperationType,
                        Collectors.summingDouble(OperationEvent::getAmount)
                ));
    }

    @Override
    public Double getTotalAmountByOperationType(String operationType) {
        return operationEventRepository.findByOperationType(operationType).stream()
                .mapToDouble(OperationEvent::getAmount)
                .sum();
    }
} 