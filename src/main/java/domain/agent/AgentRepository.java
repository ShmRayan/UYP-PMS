package domain.agent;

import java.util.List;

public interface AgentRepository {
    void save(PharmacyAgent agent);
    PharmacyAgent findById(AgentId id); 
    PharmacyAgent findByEmail(String email);
    void deactivate(PharmacyAgent agent);
    List<PharmacyAgent> findAll();
}
