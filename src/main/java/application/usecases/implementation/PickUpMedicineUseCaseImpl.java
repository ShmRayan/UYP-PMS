package application.usecases.implementation;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import application.usecases.command.PickUpMedicineCommand;
import application.usecases.interfaces.PickUpMedicineUseCase;
import domain.patient.Patient;
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
        Prescription prescription = prescriptionRepository.findById(new PrescriptionId(command.prescriptionId));
        Patient patient = new Patient(null, command.patientId, LocalDate.now(), null, null, List.of());
        prescription.markPickedUp(patient);
        prescriptionRepository.save(prescription);
    }
}
