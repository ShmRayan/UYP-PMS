package application.usecases.implementation;

import application.usecases.command.CreatePrescriptionCommand;
import application.usecases.interfaces.CreatePrescriptionUseCase;

import domain.patient.*;
import domain.prescription.*;

import domain.external.ExternalRegistryVerifier;
import domain.external.DrugDatabaseVerifier;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CreatePrescriptionUseCaseImpl implements CreatePrescriptionUseCase {

    private final PatientRepository patientRepository;
    private final PrescriptionRepository prescriptionRepository;
    private final PrescriptionFactory prescriptionFactory;

    public CreatePrescriptionUseCaseImpl(
            PatientRepository patientRepository,
            PrescriptionRepository prescriptionRepository,
            PrescriptionFactory prescriptionFactory
    ) {
        this.patientRepository = patientRepository;
        this.prescriptionRepository = prescriptionRepository;
        this.prescriptionFactory = prescriptionFactory;
    }

    @Override
    public void execute(CreatePrescriptionCommand command) {

        Patient patient = patientRepository.findByHealthId(new HealthId(command.patientId));
        if (patient == null)
            throw new IllegalArgumentException("❌ Patient introuvable.");

        if (!ExternalRegistryVerifier.isValidPrescriber(command.prescriberId))
            throw new IllegalArgumentException("❌ Prescriber non confirmé dans le registre CPSO.");

        if (!DrugDatabaseVerifier.isValidDIN(command.din))
            throw new IllegalArgumentException("❌ DIN inexistant dans la Drug Product Database.");

        PrescriptionItem item = new PrescriptionItem(
                command.drugName,
                command.din,
                command.strength,
                command.adminMethod,
                command.frequency,
                command.instructions,
                command.quantity,
                command.refillType,
                command.refillCount
        );

        Prescription prescription =
                prescriptionFactory.createExternalPrescriber(
                        patient,
                        List.of(item),
                        command.prescriberId
                );

        prescriptionRepository.save(prescription);
    }
}
