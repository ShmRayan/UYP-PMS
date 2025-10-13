package domain.security;

import domain.agent.PharmacyAgent;
import java.util.Objects;

public class AuthenticationService {
    private final UserRepository userRepository;

    public AuthenticationService(UserRepository userRepository) {
        this.userRepository = Objects.requireNonNull(userRepository);
    }

    public UserSession authenticate(UserCredentials credentials) {
        PharmacyAgent agent = userRepository.findByUsername(credentials.getUsername());

        if (agent == null)
            throw new IllegalArgumentException("Unknown username: " + credentials.getUsername());

        // Ici, on suppose que le mot de passe est simplement stocké en clair pour l’exemple
        // (dans un vrai système, il serait haché)
        if (!agent.getId().getValue().equals(credentials.getPassword()))
            throw new SecurityException("Invalid credentials");

        UserSession session = new UserSession(agent, 30); // session de 30 minutes
        userRepository.saveSession(session);
        return session;
    }
}
