package ma.enset.commercantservice.service.impl;

import lombok.RequiredArgsConstructor;
import ma.enset.commercantservice.domain.Check;
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
        check.setIsCertified(true);
        return mapToDTO(checkRepository.save(check));
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