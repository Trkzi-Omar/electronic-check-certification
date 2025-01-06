package ma.enset.banquecentraleservice.service.impl;

import lombok.RequiredArgsConstructor;
import ma.enset.banquecentraleservice.domain.BankAgency;
import ma.enset.banquecentraleservice.dto.BankAgencyRequestDTO;
import ma.enset.banquecentraleservice.dto.BankAgencyResponseDTO;
import ma.enset.banquecentraleservice.repository.BankAgencyRepository;
import ma.enset.banquecentraleservice.service.BankAgencyService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class BankAgencyServiceImpl implements BankAgencyService {
    private final BankAgencyRepository bankAgencyRepository;

    @Override
    public BankAgencyResponseDTO createAgency(BankAgencyRequestDTO agencyDTO) {
        BankAgency agency = BankAgency.builder()
                .name(agencyDTO.getName())
                .city(agencyDTO.getCity())
                .serviceURL(agencyDTO.getServiceURL())
                .build();
        return mapToDTO(bankAgencyRepository.save(agency));
    }

    @Override
    public BankAgencyResponseDTO getAgency(Long id) {
        return bankAgencyRepository.findById(id)
                .map(this::mapToDTO)
                .orElseThrow(() -> new RuntimeException("Bank Agency not found"));
    }

    @Override
    public List<BankAgencyResponseDTO> getAllAgencies() {
        return bankAgencyRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<BankAgencyResponseDTO> getAgenciesByCity(String city) {
        return bankAgencyRepository.findByCity(city).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public BankAgencyResponseDTO updateAgency(Long id, BankAgencyRequestDTO agencyDTO) {
        BankAgency agency = bankAgencyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Bank Agency not found"));
        
        agency.setName(agencyDTO.getName());
        agency.setCity(agencyDTO.getCity());
        agency.setServiceURL(agencyDTO.getServiceURL());
        
        return mapToDTO(bankAgencyRepository.save(agency));
    }

    @Override
    public void deleteAgency(Long id) {
        bankAgencyRepository.deleteById(id);
    }

    private BankAgencyResponseDTO mapToDTO(BankAgency agency) {
        return BankAgencyResponseDTO.builder()
                .id(agency.getId())
                .name(agency.getName())
                .city(agency.getCity())
                .serviceURL(agency.getServiceURL())
                .build();
    }
} 