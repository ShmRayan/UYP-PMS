package domain.agent;

public class AgentFactory {

    public PharmacyAgent createAgent(String name, AgentRole role) {
        return new PharmacyAgent(new AgentId(), name, role);
    }
}
