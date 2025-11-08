package application.usecases.implementation;

import org.springframework.stereotype.Service;

import application.usecases.command.UnregisterAgentCommand;
import application.usecases.interfaces.UnregisterAgentUseCase;
import domain.agent.AgentId;
import domain.agent.AgentRepository;
import domain.agent.PharmacyAgent;

@Service
public class UnregisterAgentUseCaseImpl implements UnregisterAgentUseCase {

    private final AgentRepository agentRepository;

    public UnregisterAgentUseCaseImpl(AgentRepository agentRepository) {
        this.agentRepository = agentRepository;
    }

    @Override
    public void execute(UnregisterAgentCommand command) {
        PharmacyAgent agent = agentRepository.findById(new AgentId(command.agentId));
        agent.deactivate();
        agentRepository.deactivate(agent);
    }
}
