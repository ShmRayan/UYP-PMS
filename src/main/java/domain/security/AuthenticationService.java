package domain.security;

import org.springframework.stereotype.Service;

import domain.agent.AgentRepository;
import domain.agent.PharmacyAgent;

@Service
public class AuthenticationService {

    private final AgentRepository agentRepository;

    public AuthenticationService(AgentRepository agentRepository) {
        this.agentRepository = agentRepository;
    }

    public PharmacyAgent authenticate(UserCredentials credentials) {
        PharmacyAgent agent = agentRepository.findByEmail(credentials.getEmail());

        if (agent == null)
            throw new IllegalArgumentException("Adresse e-mail introuvable : " + credentials.getEmail());

        if (!agent.getPassword().equals(credentials.getPassword()))
            throw new SecurityException("Mot de passe incorrect");

        return agent; 
    }
}
