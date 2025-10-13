package domain.security;

import domain.agent.PharmacyAgent;

public interface UserRepository {
    PharmacyAgent findByUsername(String username);
    void saveSession(UserSession session);
}
