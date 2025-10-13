package domain.patient;

public interface PatientRepository {
    void save(Patient patient);
    Patient findByHealthId(HealthId id);
}
