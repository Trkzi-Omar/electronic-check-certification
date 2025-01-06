package ma.enset.commercantservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class AccountDTO {
    private String number;
    private Double balance;
    private String clientReference;
    private String type;
} 