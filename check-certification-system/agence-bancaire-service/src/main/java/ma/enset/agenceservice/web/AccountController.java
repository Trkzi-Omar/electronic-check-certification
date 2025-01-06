package ma.enset.agenceservice.web;

import lombok.RequiredArgsConstructor;
import ma.enset.agenceservice.dto.*;
import ma.enset.agenceservice.service.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/agence/accounts")
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;

    @PostMapping
    public ResponseEntity<AccountResponseDTO> createAccount(@RequestBody AccountRequestDTO accountDTO) {
        return ResponseEntity.ok(accountService.createAccount(accountDTO));
    }

    @GetMapping("/{number}")
    public ResponseEntity<AccountResponseDTO> getAccount(@PathVariable String number) {
        return ResponseEntity.ok(accountService.getAccount(number));
    }

    @GetMapping
    public ResponseEntity<List<AccountResponseDTO>> getAllAccounts() {
        return ResponseEntity.ok(accountService.getAllAccounts());
    }

    @PutMapping("/{number}")
    public ResponseEntity<AccountResponseDTO> updateAccount(
            @PathVariable String number,
            @RequestBody AccountRequestDTO accountDTO) {
        return ResponseEntity.ok(accountService.updateAccount(number, accountDTO));
    }

    @PostMapping("/operations")
    public ResponseEntity<OperationResponseDTO> performOperation(@RequestBody OperationRequestDTO operationDTO) {
        return ResponseEntity.ok(accountService.performOperation(operationDTO));
    }

    @GetMapping("/{number}/operations")
    public ResponseEntity<List<OperationResponseDTO>> getAccountOperations(@PathVariable String number) {
        return ResponseEntity.ok(accountService.getAccountOperations(number));
    }

    @PostMapping("/certify-check")
    public ResponseEntity<OperationResponseDTO> certifyCheck(
            @RequestParam String checkNumber,
            @RequestParam String accountNumber,
            @RequestParam Double amount) {
        return ResponseEntity.ok(accountService.certifyCheck(checkNumber, accountNumber, amount));
    }

    @DeleteMapping("/{number}")
    public ResponseEntity<Void> deleteAccount(@PathVariable String number) {
        accountService.deleteAccount(number);
        return ResponseEntity.noContent().build();
    }
} 