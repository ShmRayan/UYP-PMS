package application.usecases.implementation;

import java.util.List;

import org.springframework.stereotype.Service;

import application.usecases.command.RegisterPatientCommand;
import application.usecases.interfaces.RegisterPatientUseCase;
import domain.patient.*;

@Service
public class RegisterPatientUseCaseImpl implements RegisterPatientUseCase {

    private final PatientRepository patientRepository;

    public RegisterPatientUseCaseImpl(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Override
    public void execute(RegisterPatientCommand command) {

        Address address = new Address(
                command.street, command.city, command.province, command.postalCode
        );

        InsuranceInfo insurance = new InsuranceInfo(
                command.policyNumber, command.provider, command.insuranceExpiry
        );

        List<Allergy> allergies = command.allergies.stream()
                .map(Allergy::new)
                .toList();

        List<Medication> meds = command.currentMedications.stream()
                .map(Medication::new)
                .toList();

        Patient patient = new Patient(
                new HealthId(command.healthId),
                command.name,
                command.dateOfBirth,
                address,
                insurance,
                command.gender,
                command.languagePreference,
                allergies,
                meds
        );

        patientRepository.save(patient);
    }
}
