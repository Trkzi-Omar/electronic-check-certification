package ma.enset.banquecentraleservice.service;

import ma.enset.banquecentraleservice.dto.BankAgencyRequestDTO;
import ma.enset.banquecentraleservice.dto.BankAgencyResponseDTO;
import java.util.List;

public interface BankAgencyService {
    BankAgencyResponseDTO createAgency(BankAgencyRequestDTO agencyDTO);
    BankAgencyResponseDTO getAgency(Long id);
    List<BankAgencyResponseDTO> getAllAgencies();
    List<BankAgencyResponseDTO> getAgenciesByCity(String city);
    BankAgencyResponseDTO updateAgency(Long id, BankAgencyRequestDTO agencyDTO);
    void deleteAgency(Long id);
} 