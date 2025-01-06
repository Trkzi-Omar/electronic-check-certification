package ma.enset.agenceservice.service;

import ma.enset.agenceservice.dto.AccountRequestDTO;
import ma.enset.agenceservice.dto.AccountResponseDTO;
import ma.enset.agenceservice.dto.OperationRequestDTO;
import ma.enset.agenceservice.dto.OperationResponseDTO;

import java.util.List;

public interface AccountService {
    AccountResponseDTO createAccount(AccountRequestDTO accountDTO);
    AccountResponseDTO getAccount(String number);
    List<AccountResponseDTO> getAllAccounts();
    AccountResponseDTO updateAccount(String number, AccountRequestDTO accountDTO);
    void deleteAccount(String number);
    
    OperationResponseDTO performOperation(OperationRequestDTO operationDTO);
    List<OperationResponseDTO> getAccountOperations(String accountNumber);
    OperationResponseDTO certifyCheck(String checkNumber, String accountNumber, Double amount);
} 