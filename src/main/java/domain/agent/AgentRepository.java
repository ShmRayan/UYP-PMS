package domain.agent;

public interface AgentRepository {
    void save(PharmacyAgent agent);
    PharmacyAgent findById(AgentId id); 
    void deactivate(PharmacyAgent agent);
}
