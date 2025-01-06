package ma.enset.banquecentraleservice.web;

import lombok.RequiredArgsConstructor;
import ma.enset.banquecentraleservice.dto.BankAgencyRequestDTO;
import ma.enset.banquecentraleservice.dto.BankAgencyResponseDTO;
import ma.enset.banquecentraleservice.service.BankAgencyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/banque-centrale/agencies")
@RequiredArgsConstructor
public class BankAgencyController {
    private final BankAgencyService bankAgencyService;

    @PostMapping
    public ResponseEntity<BankAgencyResponseDTO> createAgency(@RequestBody BankAgencyRequestDTO agencyDTO) {
        return ResponseEntity.ok(bankAgencyService.createAgency(agencyDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BankAgencyResponseDTO> getAgency(@PathVariable Long id) {
        return ResponseEntity.ok(bankAgencyService.getAgency(id));
    }

    @GetMapping
    public ResponseEntity<List<BankAgencyResponseDTO>> getAllAgencies() {
        return ResponseEntity.ok(bankAgencyService.getAllAgencies());
    }

    @GetMapping("/city/{city}")
    public ResponseEntity<List<BankAgencyResponseDTO>> getAgenciesByCity(@PathVariable String city) {
        return ResponseEntity.ok(bankAgencyService.getAgenciesByCity(city));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BankAgencyResponseDTO> updateAgency(
            @PathVariable Long id,
            @RequestBody BankAgencyRequestDTO agencyDTO) {
        return ResponseEntity.ok(bankAgencyService.updateAgency(id, agencyDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAgency(@PathVariable Long id) {
        bankAgencyService.deleteAgency(id);
        return ResponseEntity.noContent().build();
    }
} 