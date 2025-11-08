package application.usecases.implementation;

import java.util.List;

import org.springframework.stereotype.Service;

import application.usecases.command.RegisterPatientCommand;
import application.usecases.interfaces.RegisterPatientUseCase;
import domain.patient.Address;
import domain.patient.Allergy;
import domain.patient.HealthId;
import domain.patient.InsuranceInfo;
import domain.patient.Patient;
import domain.patient.PatientRepository;

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
                command.policyNumber, command.provider, command.expiryDate
        );

        List<Allergy> allergies = command.allergies;
        Patient patient = new Patient(
                new HealthId(command.healthId),
                command.name,
                command.dateOfBirth,
                address,
                insurance,
                allergies
        );

        patientRepository.save(patient);
    }
}
