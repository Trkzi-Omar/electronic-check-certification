package ma.enset.agenceservice.service.impl;

import lombok.RequiredArgsConstructor;
import ma.enset.agenceservice.domain.Account;
import ma.enset.agenceservice.domain.Operation;
import ma.enset.agenceservice.domain.OperationType;
import ma.enset.agenceservice.dto.*;
import ma.enset.agenceservice.repository.AccountRepository;
import ma.enset.agenceservice.repository.OperationRepository;
import ma.enset.agenceservice.service.AccountService;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;
    private final OperationRepository operationRepository;
    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Override
    public AccountResponseDTO createAccount(AccountRequestDTO accountDTO) {
        Account account = Account.builder()
                .number(accountDTO.getNumber())
                .balance(accountDTO.getBalance())
                .clientReference(accountDTO.getClientReference())
                .type(accountDTO.getType())
                .build();
        return mapToDTO(accountRepository.save(account));
    }

    @Override
    public AccountResponseDTO getAccount(String number) {
        return accountRepository.findById(number)
                .map(this::mapToDTO)
                .orElseThrow(() -> new RuntimeException("Account not found"));
    }

    @Override
    public List<AccountResponseDTO> getAllAccounts() {
        return accountRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public AccountResponseDTO updateAccount(String number, AccountRequestDTO accountDTO) {
        Account account = accountRepository.findById(number)
                .orElseThrow(() -> new RuntimeException("Account not found"));
        
        account.setBalance(accountDTO.getBalance());
        account.setClientReference(accountDTO.getClientReference());
        account.setType(accountDTO.getType());
        
        return mapToDTO(accountRepository.save(account));
    }

    @Override
    public void deleteAccount(String number) {
        accountRepository.deleteById(number);
    }

    @Override
    public OperationResponseDTO performOperation(OperationRequestDTO operationDTO) {
        Account account = accountRepository.findById(operationDTO.getAccountNumber())
                .orElseThrow(() -> new RuntimeException("Account not found"));

        if (operationDTO.getType() == OperationType.DEBIT && 
            account.getBalance() < operationDTO.getAmount()) {
            throw new RuntimeException("Insufficient balance");
        }

        Operation operation = Operation.builder()
                .date(LocalDateTime.now())
                .amount(operationDTO.getAmount())
                .checkNumber(operationDTO.getCheckNumber())
                .type(operationDTO.getType())
                .description(operationDTO.getDescription())
                .account(account)
                .build();

        // Update account balance
        if (operationDTO.getType() == OperationType.DEBIT) {
            account.setBalance(account.getBalance() - operationDTO.getAmount());
        } else if (operationDTO.getType() == OperationType.CREDIT) {
            account.setBalance(account.getBalance() + operationDTO.getAmount());
        }

        Operation savedOperation = operationRepository.save(operation);
        accountRepository.save(account);

        // Publish event to Kafka
        kafkaTemplate.send("operation-events", mapToOperationEvent(savedOperation));

        return mapToOperationDTO(savedOperation);
    }

    @Override
    public List<OperationResponseDTO> getAccountOperations(String accountNumber) {
        return operationRepository.findByAccountNumber(accountNumber).stream()
                .map(this::mapToOperationDTO)
                .collect(Collectors.toList());
    }

    @Override
    public OperationResponseDTO certifyCheck(String checkNumber, String accountNumber, Double amount) {
        Account account = accountRepository.findById(accountNumber)
                .orElseThrow(() -> new RuntimeException("Account not found"));

        if (account.getBalance() < amount) {
            throw new RuntimeException("Insufficient balance for check certification");
        }

        // Create certification operation
        OperationRequestDTO operationDTO = OperationRequestDTO.builder()
                .amount(amount)
                .checkNumber(checkNumber)
                .type(OperationType.CERTIFICATION_CHEQUE)
                .description("Check certification")
                .accountNumber(accountNumber)
                .build();

        return performOperation(operationDTO);
    }

    private AccountResponseDTO mapToDTO(Account account) {
        return AccountResponseDTO.builder()
                .number(account.getNumber())
                .balance(account.getBalance())
                .clientReference(account.getClientReference())
                .type(account.getType())
                .operations(account.getOperations().stream()
                        .map(this::mapToOperationDTO)
                        .collect(Collectors.toList()))
                .build();
    }

    private OperationResponseDTO mapToOperationDTO(Operation operation) {
        return OperationResponseDTO.builder()
                .id(operation.getId())
                .date(operation.getDate())
                .amount(operation.getAmount())
                .checkNumber(operation.getCheckNumber())
                .type(operation.getType())
                .description(operation.getDescription())
                .accountNumber(operation.getAccount().getNumber())
                .build();
    }

    private OperationEvent mapToOperationEvent(Operation operation) {
        return OperationEvent.builder()
                .operationType(operation.getType().toString())
                .accountNumber(operation.getAccount().getNumber())
                .amount(operation.getAmount())
                .timestamp(operation.getDate())
                .checkNumber(operation.getCheckNumber())
                .status("COMPLETED")
                .build();
    }
} 