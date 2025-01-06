package ma.enset.agenceservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OperationEvent {
    private String operationType;
    private String accountNumber;
    private Double amount;
    private LocalDateTime timestamp;
    private String checkNumber;
    private String status;
} 