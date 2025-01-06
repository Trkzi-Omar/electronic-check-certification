package ma.enset.banquecentraleservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class BankAgencyResponseDTO {
    private Long id;
    private String name;
    private String city;
    private String serviceURL;
} 