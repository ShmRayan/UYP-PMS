package infrastructure.repository.inmemory;

import java.util.HashMap;
import java.util.Map;

import domain.agent.AgentId;
import domain.agent.AgentRepository;
import domain.agent.PharmacyAgent;

public class InMemoryAgentRepository implements AgentRepository {

    private final Map<String, PharmacyAgent> agents = new HashMap<>();

    @Override
    public void save(PharmacyAgent agent) {
        agents.put(agent.getId(), agent); 
    }

    @Override
    public PharmacyAgent findById(AgentId id) { 
        return agents.get(id.getValue()); 
    }

    @Override
    public void deactivate(PharmacyAgent agent) {
        agent.deactivate();
        agents.put(agent.getId(), agent);
    }
}
