package application.usecases.implementation;

import org.springframework.stereotype.Service;

import application.usecases.command.UpdatePatientCommand;
import application.usecases.interfaces.UpdatePatientUseCase;
import domain.patient.Address;
import domain.patient.HealthId;
import domain.patient.InsuranceInfo;
import domain.patient.Patient;
import domain.patient.PatientRepository;

@Service
public class UpdatePatientUseCaseImpl implements UpdatePatientUseCase {

    private final PatientRepository patientRepository;

    public UpdatePatientUseCaseImpl(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Override
    public void execute(UpdatePatientCommand command) {
        Patient patient = patientRepository.findByHealthId(new HealthId(command.healthId));

        Address newAddress = new Address(
                command.street, command.city, command.province, command.postalCode
        );

        InsuranceInfo newInsurance = new InsuranceInfo(
                command.policyNumber, command.provider, command.expiryDate
        );

        patient.updateInfo(newAddress, newInsurance);
        patientRepository.save(patient);
    }
}
