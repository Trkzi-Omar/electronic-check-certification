package ma.enset.commercantservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class BankAgencyDTO {
    private Long id;
    private String name;
    private String city;
    private String serviceURL;
} 