package ma.enset.agenceservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.enset.agenceservice.domain.OperationType;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class OperationRequestDTO {
    private Double amount;
    private String checkNumber;
    private OperationType type;
    private String description;
    private String accountNumber;
} 