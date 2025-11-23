package domain.agent;

import org.springframework.stereotype.Component;

@Component
public class AgentFactory {

    public PharmacyAgent createAgentWithCustomId(String id, String name, String email, AgentRole role) {

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
