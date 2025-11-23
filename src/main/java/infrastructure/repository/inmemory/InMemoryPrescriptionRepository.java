package infrastructure.repository.inmemory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import domain.patient.Patient;
import domain.prescription.Prescription;
import domain.prescription.PrescriptionId;
import domain.prescription.PrescriptionRepository;
import domain.reporting.ReportPeriod;

public class InMemoryPrescriptionRepository implements PrescriptionRepository {

    private final Map<String, Prescription> prescriptions = new HashMap<>();

    @Override
    public void save(Prescription prescription) {
        prescriptions.put(prescription.getId().getValue(), prescription);
    }

    @Override
    public Prescription findById(PrescriptionId id) {
        return prescriptions.get(id.getValue());
    }

    @Override
    public List<Prescription> findByPatient(Patient patient) {
        return prescriptions.values().stream()
                .filter(p -> p.getPatient().equals(patient))
                .toList();
    }

    @Override
    public List<Prescription> findByDINAndPeriod(String din, ReportPeriod period) {
        return prescriptions.values().stream()
                .filter(p -> p.getItems().stream()
                        .anyMatch(i -> i.getDin().equals(din)))
                .toList();
    }

    @Override
    public List<Prescription> findAll() {
        return prescriptions.values().stream().toList();
    }

    @Override
    public String findLastId() {
        return prescriptions.keySet()
                .stream()
                .sorted()
                .reduce((first, second) -> second)
                .orElse(null);
    }
}
