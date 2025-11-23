package application.usecases.implementation;

import org.springframework.stereotype.Service;

import application.usecases.command.PickUpMedicineCommand;
import application.usecases.interfaces.PickUpMedicineUseCase;
import domain.prescription.Prescription;
import domain.prescription.PrescriptionId;
import domain.prescription.PrescriptionRepository;

@Service
public class PickUpMedicineUseCaseImpl implements PickUpMedicineUseCase {

    private final PrescriptionRepository prescriptionRepository;

    public PickUpMedicineUseCaseImpl(PrescriptionRepository prescriptionRepository) {
        this.prescriptionRepository = prescriptionRepository;
    }

    @Override
    public void execute(PickUpMedicineCommand command) {

        Prescription prescription =
                prescriptionRepository.findById(new PrescriptionId(command.prescriptionId));

        if (prescription == null)
            throw new IllegalArgumentException("Prescription not found.");

        prescription.markPickedUp(command.pickupNotes);

        prescriptionRepository.save(prescription);
    }
}
