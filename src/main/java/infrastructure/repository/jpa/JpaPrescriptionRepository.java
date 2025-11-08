package infrastructure.repository.jpa;

import domain.patient.Patient;
import domain.prescription.Prescription;
import domain.prescription.PrescriptionId;
import domain.prescription.PrescriptionRepository;
import domain.reporting.ReportPeriod;
import infrastructure.repository.jpa.entities.PrescriptionEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class JpaPrescriptionRepository implements PrescriptionRepository {

    private final SpringDataPrescriptionRepository jpaRepo;

    @Autowired
    public JpaPrescriptionRepository(SpringDataPrescriptionRepository jpaRepo) {
        this.jpaRepo = jpaRepo;
    }

    @Override
    public void save(Prescription prescription) {
        String din = "UNKNOWN";
        int totalQuantity = prescription.getItems().stream()
                .mapToInt(item -> item.getQuantity()) 
                .sum();

        PrescriptionEntity entity = new PrescriptionEntity(
            prescription.getId().getValue(),
            prescription.getPatient().getHealthId().getValue(),
            din,
            totalQuantity,
            prescription.getDate()
        );
        jpaRepo.save(entity);
    }

    @Override
    public Prescription findById(PrescriptionId id) {
        return jpaRepo.findById(id.getValue())
                .map(e -> new Prescription(
                        new PrescriptionId(e.getId()),
                        e.getDate(),
                        List.of(),  // pas d'items
                        null,       // pas de preparedBy
                        null        // pas de patient
                ))
                .orElse(null);
    }

    @Override
    public List<Prescription> findByPatient(Patient patient) {
        return jpaRepo.findByPatientId(patient.getHealthId().getValue())
                .stream()
                .map(e -> new Prescription(
                        new PrescriptionId(e.getId()),
                        e.getDate(),
                        List.of(),
                        null,
                        patient
                ))
                .collect(Collectors.toList());
    }

    @Override
    public List<Prescription> findByDINAndPeriod(String din, ReportPeriod period) {
        return jpaRepo.findByDinAndDateBetween(din, period.getStartDate(), period.getEndDate())
                .stream()
                .map(e -> new Prescription(
                        new PrescriptionId(e.getId()),
                        e.getDate(),
                        List.of(),
                        null,
                        null
                ))
                .collect(Collectors.toList());
    }
}
