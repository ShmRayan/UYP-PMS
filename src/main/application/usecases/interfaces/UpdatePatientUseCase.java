package application.usecases.interfaces;

import application.usecases.command.UpdatePatientCommand;

public interface UpdatePatientUseCase {
    void execute(UpdatePatientCommand command);
}
