package infrastructure.repository.jpa;

import domain.agent.PharmacyAgent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringDataAgentRepository extends JpaRepository<PharmacyAgent, String> {
}
