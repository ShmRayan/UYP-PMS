package domain.prescription;

import java.util.List;

import domain.patient.Patient;
import domain.reporting.ReportPeriod;

public interface PrescriptionRepository {
    void save(Prescription prescription);
    Prescription findById(PrescriptionId id);
    List<Prescription> findByPatient(Patient patient);
    List<Prescription> findByDINAndPeriod(String din, ReportPeriod period);
}
