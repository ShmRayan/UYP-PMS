package application.usecases.interfaces;

import application.usecases.command.RegisterAgentCommand;

public interface RegisterAgentUseCase {
    void execute(RegisterAgentCommand command);
}
