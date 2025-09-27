package application.usecases.interfaces;

import application.usecases.command.PreparePrescriptionFillCommand;

public interface PreparePrescriptionFillUseCase {
    void execute(PreparePrescriptionFillCommand command);
}
