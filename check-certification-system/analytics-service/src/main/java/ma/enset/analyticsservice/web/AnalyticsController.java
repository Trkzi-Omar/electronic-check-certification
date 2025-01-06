package ma.enset.analyticsservice.web;

import lombok.RequiredArgsConstructor;
import ma.enset.analyticsservice.domain.OperationEvent;
import ma.enset.analyticsservice.service.AnalyticsService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/analytics")
@RequiredArgsConstructor
public class AnalyticsController {
    private final AnalyticsService analyticsService;

    @GetMapping("/events")
    public ResponseEntity<List<OperationEvent>> getAllEvents() {
        return ResponseEntity.ok(analyticsService.getAllEvents());
    }

    @GetMapping("/events/by-date-range")
    public ResponseEntity<List<OperationEvent>> getEventsByTimeRange(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end) {
        return ResponseEntity.ok(analyticsService.getEventsByTimeRange(start, end));
    }

    @GetMapping("/events/by-type/{operationType}")
    public ResponseEntity<List<OperationEvent>> getEventsByType(@PathVariable String operationType) {
        return ResponseEntity.ok(analyticsService.getEventsByType(operationType));
    }

    @GetMapping("/stats/by-type")
    public ResponseEntity<Map<String, Double>> getOperationStatsByType() {
        return ResponseEntity.ok(analyticsService.getOperationStatsByType());
    }

    @GetMapping("/stats/by-account/{accountNumber}")
    public ResponseEntity<Map<String, Double>> getOperationStatsByAccount(@PathVariable String accountNumber) {
        return ResponseEntity.ok(analyticsService.getOperationStatsByAccount(accountNumber));
    }

    @GetMapping("/stats/total-by-type/{operationType}")
    public ResponseEntity<Double> getTotalAmountByOperationType(@PathVariable String operationType) {
        return ResponseEntity.ok(analyticsService.getTotalAmountByOperationType(operationType));
    }
} 