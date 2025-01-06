package ma.enset.analyticsservice.service;

import lombok.RequiredArgsConstructor;
import ma.enset.analyticsservice.domain.OperationEvent;
import ma.enset.analyticsservice.repository.OperationEventRepository;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaConsumerService {
    private final OperationEventRepository operationEventRepository;

    @KafkaListener(topics = "operation-events", groupId = "analytics-group")
    public void consume(OperationEvent event) {
        // Save the event to the database
        operationEventRepository.save(event);
        
        // Log the event (optional)
        System.out.println("Event received: " + event);
    }
} 