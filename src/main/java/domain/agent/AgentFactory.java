package domain.agent;

import org.springframework.stereotype.Component;

@Component
public class AgentFactory {
    public PharmacyAgent createAgent(String name, AgentRole role) {
        return new PharmacyAgent(new AgentId(), name, name.toLowerCase() + "@uypms.com", "default123", role);
    }
}
