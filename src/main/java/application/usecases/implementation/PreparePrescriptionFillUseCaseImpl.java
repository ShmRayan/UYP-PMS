package application.usecases.implementation;

import org.springframework.stereotype.Service;

import application.usecases.command.PreparePrescriptionFillCommand;
import application.usecases.interfaces.PreparePrescriptionFillUseCase;
import domain.prescription.Prescription;
import domain.prescription.PrescriptionId;
import domain.prescription.PrescriptionRepository;

@Service
public class PreparePrescriptionFillUseCaseImpl implements PreparePrescriptionFillUseCase {

    private final PrescriptionRepository prescriptionRepository;

    public PreparePrescriptionFillUseCaseImpl(PrescriptionRepository prescriptionRepository) {
        this.prescriptionRepository = prescriptionRepository;
    }

    @Override
    public void execute(PreparePrescriptionFillCommand command) {

        Prescription prescription =
                prescriptionRepository.findById(new PrescriptionId(command.prescriptionId));

        if (prescription == null)
            throw new IllegalArgumentException("Prescription not found.");

        prescription.markPrepared(
                command.lotNumber,
                command.expiryDate
        );

        prescriptionRepository.save(prescription);
    }
}
