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
        PharmacyAgent agent = agentFactory.createAgent(command.name, role);
        agentRepository.save(agent);
    }
}
