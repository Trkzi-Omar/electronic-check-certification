package ma.enset.commercantservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class CheckRequestDTO {
    private String checkNumber;
    private String bankCode;
    private String accountNumber;
    private String clientName;
    private Double amount;
} 