package infrastructure.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.agent.PharmacyAgent;
import domain.security.UserRepository;
import domain.security.UserSession;

@Repository
public interface JpaUserRepository extends JpaRepository<PharmacyAgent, String>, UserRepository {
    PharmacyAgent findByEmail(String email);

    @Override
    default PharmacyAgent findByUsername(String username) {
        return findByEmail(username); // On fait correspondre username = email
    }

    @Override
    default void saveSession(UserSession session) {
    }
}
