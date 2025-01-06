package ma.enset.commercantservice.client;

import ma.enset.commercantservice.dto.AccountDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "AGENCE-BANCAIRE-SERVICE")
public interface AccountClient {
    
    @GetMapping("/api/agence/accounts/{number}")
    AccountDTO getAccount(@PathVariable String number);
    
    @PostMapping("/api/agence/accounts/certify-check")
    void certifyCheck(
            @RequestParam String checkNumber,
            @RequestParam String accountNumber,
            @RequestParam Double amount);
} 