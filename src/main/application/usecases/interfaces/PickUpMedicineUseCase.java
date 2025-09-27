package application.usecases.interfaces;

import application.usecases.command.PickUpMedicineCommand;

public interface PickUpMedicineUseCase {
    void execute(PickUpMedicineCommand command);
}
