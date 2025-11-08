package application.usecases.interfaces;

import application.usecases.command.RegisterPatientCommand;

public interface RegisterPatientUseCase {
    void execute(RegisterPatientCommand command);
}
