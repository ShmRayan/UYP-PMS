package application.usecases.implementation;

import org.springframework.stereotype.Service;

import application.usecases.command.RegisterAgentCommand;
import application.usecases.interfaces.RegisterAgentUseCase;
import domain.agent.AgentFactory;
import domain.agent.AgentRepository;
import domain.agent.AgentRole;
import domain.agent.PharmacyAgent;

@Service
public class RegisterAgentUseCaseImpl implements RegisterAgentUseCase {

    private final AgentRepository agentRepository;
    private final AgentFactory agentFactory;

    public RegisterAgentUseCaseImpl(AgentRepository agentRepository, AgentFactory agentFactory) {
        this.agentRepository = agentRepository;
        this.agentFactory = agentFactory;
    }

    @Override
    public void execute(RegisterAgentCommand command) {

        AgentRole role = AgentRole.valueOf(command.role.toUpperCase());

        String prefix = switch (role) {
            case PHARMACIST -> "pharm-";
            case ASSISTANT -> "assist-";
            case ADMIN -> "admin-";
        };

        int nextNumber;
        nextNumber = agentRepository.findAll().stream()
                .filter(a -> a.getId().startsWith(prefix))
                .map(a -> {
                    try {
                        return Integer.valueOf(a.getId().substring(prefix.length()));
                    } catch (NumberFormatException e) {
                        return 0;
                    }
                })
                .max(Integer::compare)
                .orElse(0) + 1;

        // ID généré : pharm-001, assist-001, admin-001, ...
        String newId = prefix + String.format("%03d", nextNumber);

        // Création de l’agent
        PharmacyAgent agent = agentFactory.createAgentWithCustomId(
                newId,
                command.name,
                command.email,
                role
        );

        agentRepository.save(agent);
    }
}
