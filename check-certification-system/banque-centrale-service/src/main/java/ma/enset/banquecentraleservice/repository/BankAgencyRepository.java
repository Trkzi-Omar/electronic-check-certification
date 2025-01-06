package ma.enset.banquecentraleservice.repository;

import ma.enset.banquecentraleservice.domain.BankAgency;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BankAgencyRepository extends JpaRepository<BankAgency, Long> {
    List<BankAgency> findByCity(String city);
    BankAgency findByName(String name);
} 