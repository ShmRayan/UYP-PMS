package infrastructure.repository.inmemory;

import java.util.HashMap;
import java.util.Map;

import domain.agent.PharmacyAgent;
import domain.security.UserRepository;
import domain.security.UserSession;

public class InMemoryUserRepository implements UserRepository {

    private final Map<String, PharmacyAgent> users = new HashMap<>();
    private final Map<String, UserSession> sessions = new HashMap<>();

    @Override
    public PharmacyAgent findByUsername(String username) {
        return users.get(username);
    }

    @Override
    public void saveSession(UserSession session) {
        sessions.put(session.getSessionId(), session);
    }

    
    public void saveUser(String username, PharmacyAgent agent) {
        users.put(username, agent);
    }

    
    public UserSession findSession(String sessionId) {
        return sessions.get(sessionId);
    }
}
