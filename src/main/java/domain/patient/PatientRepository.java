package domain.patient;

import java.util.List;

public interface PatientRepository {
    void save(Patient patient);
    Patient findByHealthId(HealthId id);
    List<Patient> findAll(); 
}
