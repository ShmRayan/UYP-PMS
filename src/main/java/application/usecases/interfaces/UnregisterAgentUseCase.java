package application.usecases.interfaces;

import application.usecases.command.UnregisterAgentCommand;

public interface UnregisterAgentUseCase {
    void execute(UnregisterAgentCommand command);
}
