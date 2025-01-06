package ma.enset.commercantservice.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Check {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String checkNumber;
    private String bankCode;
    private String accountNumber;
    private String clientName;
    private Double amount;
    private Boolean isCertified;
} 