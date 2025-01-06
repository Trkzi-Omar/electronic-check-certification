package ma.enset.agenceservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.enset.agenceservice.domain.AccountType;
import java.util.List;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class AccountResponseDTO {
    private String number;
    private Double balance;
    private String clientReference;
    private AccountType type;
    private List<OperationResponseDTO> operations;
} 