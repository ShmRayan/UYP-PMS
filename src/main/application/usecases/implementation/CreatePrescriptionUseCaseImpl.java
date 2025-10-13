package application.usecases.implementation;

import java.time.LocalDate;
import java.util.List;

import application.usecases.command.CreatePrescriptionCommand;
import application.usecases.interfaces.CreatePrescriptionUseCase;
import domain.agent.AgentId;
import domain.agent.AgentRole;
import domain.agent.PharmacyAgent;
import domain.patient.Patient;
import domain.prescription.Prescription;
import domain.prescription.PrescriptionFactory;
import domain.prescription.PrescriptionItem;
import domain.prescription.PrescriptionRepository;

public class CreatePrescriptionUseCaseImpl implements CreatePrescriptionUseCase {

    private final PrescriptionRepository prescriptionRepository;
    private final PrescriptionFactory prescriptionFactory;

    public CreatePrescriptionUseCaseImpl(PrescriptionRepository prescriptionRepository,
                                        PrescriptionFactory prescriptionFactory) {
        this.prescriptionRepository = prescriptionRepository;
        this.prescriptionFactory = prescriptionFactory;
    }

    @Override
    public void execute(CreatePrescriptionCommand command) {
        Patient patient = new Patient(null, command.patientId, LocalDate.now(), null, null, List.of());
        PharmacyAgent prescriber = new PharmacyAgent(new AgentId(), command.prescriberId, AgentRole.PHARMACIST);

        PrescriptionItem item = new PrescriptionItem(
                "Medication", command.din, "1 pill/day", command.instructions
        );

        Prescription prescription = prescriptionFactory.create(patient, List.of(item), prescriber);
        prescriptionRepository.save(prescription);
    }
}
