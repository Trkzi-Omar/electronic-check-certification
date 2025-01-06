package ma.enset.agenceservice.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Account {
    @Id
    private String number;
    private Double balance;
    private String clientReference;
    @Enumerated(EnumType.STRING)
    private AccountType type;
    
    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    private List<Operation> operations;
} 