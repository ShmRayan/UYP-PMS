package infrastructure.repository.inmemory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import domain.patient.HealthId;
import domain.patient.Patient;
import domain.patient.PatientRepository;

public class InMemoryPatientRepository implements PatientRepository {

    private final Map<String, Patient> patients = new HashMap<>();

    @Override
    public void save(Patient patient) {
        patients.put(patient.getHealthId().getValue(), patient);
    }

    @Override
    public Patient findByHealthId(HealthId id) {
        return patients.get(id.getValue());
    }

     @Override
    public List<Patient> findAll() {
        return patients.values().stream().toList();
    }
}
