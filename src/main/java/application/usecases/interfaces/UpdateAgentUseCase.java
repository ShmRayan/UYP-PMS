package application.usecases.interfaces;

import application.usecases.command.UpdateAgentCommand;

public interface UpdateAgentUseCase {
    void execute(UpdateAgentCommand command);
}
