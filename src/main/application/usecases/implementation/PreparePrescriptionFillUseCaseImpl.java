package application.usecases.implementation;

import application.usecases.command.PreparePrescriptionFillCommand;
import application.usecases.interfaces.PreparePrescriptionFillUseCase;
import domain.prescription.Prescription;
import domain.prescription.PrescriptionId;
import domain.prescription.PrescriptionRepository;

public class PreparePrescriptionFillUseCaseImpl implements PreparePrescriptionFillUseCase {

    private final PrescriptionRepository prescriptionRepository;

    public PreparePrescriptionFillUseCaseImpl(PrescriptionRepository prescriptionRepository) {
        this.prescriptionRepository = prescriptionRepository;
    }

    @Override
    public void execute(PreparePrescriptionFillCommand command) {
        Prescription prescription = prescriptionRepository.findById(new PrescriptionId(command.prescriptionId));
        prescription.markPrepared(null); // agent fictif ici
        prescriptionRepository.save(prescription);
    }
}
