package ma.enset.agenceservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class AgenceBancaireServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(AgenceBancaireServiceApplication.class, args);
    }
}
