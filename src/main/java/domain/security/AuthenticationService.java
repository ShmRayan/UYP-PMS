package domain.security;

import java.util.Objects;

import domain.agent.PharmacyAgent;

public class AuthenticationService {
    private final UserRepository userRepository;

    public AuthenticationService(UserRepository userRepository) {
        this.userRepository = Objects.requireNonNull(userRepository);
    }

    public UserSession authenticate(UserCredentials credentials) {
        PharmacyAgent agent = userRepository.findByUsername(credentials.getUsername());

        if (agent == null)
            throw new IllegalArgumentException("Unknown username: " + credentials.getUsername());

        if (!agent.getPassword().equals(credentials.getPassword()))
            throw new SecurityException("Invalid credentials");

        UserSession session = new UserSession(agent, 30);
        userRepository.saveSession(session);
        return session;
    }
}
