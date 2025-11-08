package application.usecases.interfaces;

import application.usecases.command.CreatePrescriptionCommand;

public interface CreatePrescriptionUseCase {
    void execute(CreatePrescriptionCommand command);
}
