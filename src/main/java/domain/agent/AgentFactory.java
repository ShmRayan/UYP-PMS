package domain.agent;

import org.springframework.stereotype.Component;

@Component
public class AgentFactory {

    private int adminCount = 1;
    private int pharmacistCount = 1;
    private int assistantCount = 1;

    public PharmacyAgent createAgent(String name, String email, AgentRole role) {

        String id;

        switch (role) {
            case ADMIN -> id = String.format("admin-%03d", adminCount++);
            case PHARMACIST -> id = String.format("pharm-%03d", pharmacistCount++);
            case ASSISTANT -> id = String.format("assist-%03d", assistantCount++);
            default -> throw new IllegalArgumentException("Role inconnu");
        }

        String defaultPassword = switch (role) {
            case ADMIN -> "admin123";
            case PHARMACIST -> "pharm123";
            case ASSISTANT -> "assist123";
        };

        return new PharmacyAgent(
                new AgentId(id),
                name,
                email,
                defaultPassword,
                role
        );
    }
}
