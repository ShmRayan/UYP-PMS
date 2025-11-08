import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {
        "application",
        "domain",
        "infrastructure"
})
@EnableJpaRepositories(basePackages = {
        "infrastructure.repository.jpa"
})
@EntityScan(basePackages = {
        "domain.prescription",
        "domain.reporting",
        "domain.agent",
        "domain.patient",
        "infrastructure.repository.jpa.entities" 
})

public class UypPmsApplication {
    public static void main(String[] args) {
        SpringApplication.run(UypPmsApplication.class, args);
    }
}
