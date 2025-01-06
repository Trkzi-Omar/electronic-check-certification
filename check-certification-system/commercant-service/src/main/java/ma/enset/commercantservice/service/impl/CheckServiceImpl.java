package ma.enset.commercantservice.service.impl;

import lombok.RequiredArgsConstructor;
import ma.enset.commercantservice.client.AccountClient;
import ma.enset.commercantservice.client.BankAgencyClient;
import ma.enset.commercantservice.domain.Check;
import ma.enset.commercantservice.dto.AccountDTO;
import ma.enset.commercantservice.dto.CheckRequestDTO;
import ma.enset.commercantservice.dto.CheckResponseDTO;
import ma.enset.commercantservice.repository.CheckRepository;
import ma.enset.commercantservice.service.CheckService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class CheckServiceImpl implements CheckService {
    private final CheckRepository checkRepository;
    private final BankAgencyClient bankAgencyClient;
    private final AccountClient accountClient;

    @Override
    public CheckResponseDTO saveCheck(CheckRequestDTO checkDTO) {
        Check check = Check.builder()
                .checkNumber(checkDTO.getCheckNumber())
                .bankCode(checkDTO.getBankCode())
                .accountNumber(checkDTO.getAccountNumber())
                .clientName(checkDTO.getClientName())
                .amount(checkDTO.getAmount())
                .isCertified(false)
                .build();
        Check savedCheck = checkRepository.save(check);
        return mapToDTO(savedCheck);
    }

    @Override
    public CheckResponseDTO getCheck(Long id) {
        Check check = checkRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Check not found"));
        return mapToDTO(check);
    }

    @Override
    public List<CheckResponseDTO> getAllChecks() {
        return checkRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CheckResponseDTO certifyCheck(String checkNumber) {
        Check check = checkRepository.findByCheckNumber(checkNumber);
        if (check == null) {
            throw new RuntimeException("Check not found");
        }

        // Verify account exists and has sufficient balance
        AccountDTO account = accountClient.getAccount(check.getAccountNumber());
        if (account == null) {
            throw new RuntimeException("Account not found");
        }

        // Request check certification from bank
        try {
            accountClient.certifyCheck(checkNumber, check.getAccountNumber(), check.getAmount());
            check.setIsCertified(true);
            return mapToDTO(checkRepository.save(check));
        } catch (Exception e) {
            throw new RuntimeException("Check certification failed: " + e.getMessage());
        }
    }

    @Override
    public void deleteCheck(Long id) {
        checkRepository.deleteById(id);
    }

    private CheckResponseDTO mapToDTO(Check check) {
        return CheckResponseDTO.builder()
                .id(check.getId())
                .checkNumber(check.getCheckNumber())
                .bankCode(check.getBankCode())
                .accountNumber(check.getAccountNumber())
                .clientName(check.getClientName())
                .amount(check.getAmount())
                .isCertified(check.getIsCertified())
                .build();
    }
} 