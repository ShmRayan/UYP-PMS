package application.usecases.implementation;

import org.springframework.stereotype.Service;

import application.usecases.command.UpdateAgentCommand;
import application.usecases.interfaces.UpdateAgentUseCase;
import domain.agent.AgentId;
import domain.agent.AgentRepository;
import domain.agent.AgentRole;
import domain.agent.PharmacyAgent;

@Service
public class UpdateAgentUseCaseImpl implements UpdateAgentUseCase {

    private final AgentRepository agentRepository;

    public UpdateAgentUseCaseImpl(AgentRepository agentRepository) {
        this.agentRepository = agentRepository;
    }

    @Override
    public void execute(UpdateAgentCommand command) {

        PharmacyAgent agent = agentRepository.findById(new AgentId(command.agentId));

        // Update name + role
        AgentRole newRole = AgentRole.valueOf(command.role.toUpperCase());
        agent.updateInfo(command.name, newRole);

        // Update email manually
        if (command.email != null && !command.email.isBlank()) {
            agent.updateEmail(command.email);
        }

        // Update password only if provided
        if (command.newPassword != null && !command.newPassword.isBlank()) {
            agent.updatePassword(command.newPassword);
        }

        agentRepository.save(agent);
    }
}
