package domain.agent;

public interface AgentRepository {
    void save(PharmacyAgent agent);
    PharmacyAgent findById(AgentId id); 
    PharmacyAgent findByEmail(String email);
    void deactivate(PharmacyAgent agent);
}
