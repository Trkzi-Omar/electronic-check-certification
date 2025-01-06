package ma.enset.commercantservice.service;

import ma.enset.commercantservice.dto.CheckRequestDTO;
import ma.enset.commercantservice.dto.CheckResponseDTO;
import java.util.List;

public interface CheckService {
    CheckResponseDTO saveCheck(CheckRequestDTO checkDTO);
    CheckResponseDTO getCheck(Long id);
    List<CheckResponseDTO> getAllChecks();
    CheckResponseDTO certifyCheck(String checkNumber);
    void deleteCheck(Long id);
} 