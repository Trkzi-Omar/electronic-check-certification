package ma.enset.commercantservice.client;

import ma.enset.commercantservice.dto.BankAgencyDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "BANQUE-CENTRALE-SERVICE")
public interface BankAgencyClient {
    
    @GetMapping("/api/banque-centrale/agencies")
    List<BankAgencyDTO> getAllAgencies();
    
    @GetMapping("/api/banque-centrale/agencies/{id}")
    BankAgencyDTO getAgency(@PathVariable Long id);
    
    @GetMapping("/api/banque-centrale/agencies/city/{city}")
    List<BankAgencyDTO> getAgenciesByCity(@PathVariable String city);
} 