package application.usecases.implementation;

import org.springframework.stereotype.Service;

import application.usecases.command.UpdatePatientCommand;
import application.usecases.interfaces.UpdatePatientUseCase;
import domain.patient.*;
import java.util.List;

@Service
public class UpdatePatientUseCaseImpl implements UpdatePatientUseCase {

    private final PatientRepository patientRepository;

    public UpdatePatientUseCaseImpl(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Override
    public void execute(UpdatePatientCommand command) {

        Patient patient = patientRepository.findByHealthId(new HealthId(command.healthId));

        if (patient == null)
            throw new IllegalArgumentException("Patient not found.");

        Address newAddress = new Address(
                command.street,
                command.city,
                command.province,
                command.postalCode
        );

        InsuranceInfo newInsurance = new InsuranceInfo(
                command.policyNumber,
                command.provider,
                command.expiryDate
        );

        List<Allergy> allergies = command.allergies.stream()
                .map(Allergy::new)
                .toList();

        List<Medication> meds = command.currentMedications.stream()
                .map(Medication::new)
                .toList();

        patient.updateInfo(
                newAddress,
                newInsurance,
                command.gender,
                command.languagePreference,
                allergies,
                meds
        );

        patientRepository.save(patient);
    }
}
