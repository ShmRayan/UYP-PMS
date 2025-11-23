package infrastructure.configuration;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import domain.agent.AgentId;
import domain.agent.AgentRepository;
import domain.agent.AgentRole;
import domain.agent.PharmacyAgent;

@Component
public class DataInitializer implements CommandLineRunner {

    private final AgentRepository agentRepository;

    public DataInitializer(AgentRepository agentRepository) {
        this.agentRepository = agentRepository;
    }

    @Override
    public void run(String... args) {

        boolean adminExists = agentRepository.findAll().stream()
                .anyMatch(a -> a.getRole() == AgentRole.ADMIN);

        if (!adminExists) {
            System.out.println("Aucun admin trouvé. Création...");

            PharmacyAgent admin = new PharmacyAgent(
                    new AgentId("admin-001"),
                    "Administrator",
                    "admin@uyp.com",
                    "admin123",
                    AgentRole.ADMIN
            );

            agentRepository.save(admin);

            System.out.println("✔ Admin créé : admin@uyp.com / admin123");
        }
    }
}
