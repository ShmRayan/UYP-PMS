package infrastructure.repository.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import domain.agent.AgentId;
import domain.agent.AgentRepository;
import domain.agent.PharmacyAgent;
@Repository
public class JpaAgentRepositoryImpl implements AgentRepository {

    private final SpringDataAgentRepository jpaRepository;

    @Autowired
    public JpaAgentRepositoryImpl(SpringDataAgentRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public void save(PharmacyAgent agent) {
        jpaRepository.save(agent);
    }

    @Override
    public PharmacyAgent findById(AgentId id) {
        return jpaRepository.findById(id.getValue()).orElse(null);
    }

    @Override
    public PharmacyAgent findByEmail(String email) {
        return jpaRepository.findByEmail(email).orElse(null);
    }

    @Override
    public void deactivate(PharmacyAgent agent) {
        agent.deactivate();
        jpaRepository.save(agent);
    }
}
