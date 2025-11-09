package infrastructure.repository.jpa;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.agent.PharmacyAgent;

@Repository
public interface SpringDataAgentRepository extends JpaRepository<PharmacyAgent, String> {
    Optional<PharmacyAgent> findByEmail(String email);
}
