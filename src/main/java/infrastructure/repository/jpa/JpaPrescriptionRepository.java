package infrastructure.repository.jpa;

import domain.patient.HealthId;
import domain.patient.InsuranceInfo;
import domain.patient.Patient;
import domain.prescription.*;
import domain.reporting.ReportPeriod;
import infrastructure.repository.jpa.entities.PrescriptionEntity;

import org.springframework.stereotype.Repository;

import java.util.List;

import domain.patient.Address;

@Repository
public class JpaPrescriptionRepository implements PrescriptionRepository {

    private final SpringDataPrescriptionRepository jpaRepo;
    private final SpringDataPatientRepository patientRepo;

    public JpaPrescriptionRepository(
            SpringDataPrescriptionRepository jpaRepo,
            SpringDataPatientRepository patientRepo
    ) {
        this.jpaRepo = jpaRepo;
        this.patientRepo = patientRepo;
    }

        @Override
        public void save(Prescription prescription) {

        PrescriptionItem item = prescription.getItems().get(0);

        PrescriptionEntity entity = new PrescriptionEntity(
                prescription.getId().getValue(),
                prescription.getDate(),
                item.getDin(),
                prescription.getPatient().getHealthId().getValue(),
                item.getQuantity(),
                item.getDrugName(),
                item.getFrequency(),
                item.getInstructions(),
                prescription.getPrescriberId(),
                item.getRefillCount(),
                item.getRefillType(),
                item.getStrength(),
                item.getAdministrationMethod(),
                prescription.getStatus().name(),
                prescription.getLotNumber(),
                prescription.getExpiryDate(),
                prescription.getPickupNotes(),
                prescription.getPickedUpAt()
        );

        jpaRepo.save(entity);
        }

        private Prescription buildDomain(PrescriptionEntity e) {

        Prescription p = new Prescription(
                new PrescriptionId(e.getId()),
                e.getDate(),
                List.of(rebuildItem(e)),
                e.getPrescriberId(),
                rebuildPatient(e.getPatientId()),
                PrescriptionStatus.valueOf(e.getStatus())
        );

        p.setLotNumber(e.getLotNumber());
        p.setExpiryDate(e.getExpiryDate());
        p.setPickupNotes(e.getPickupNotes());
        p.setPickedUpAt(e.getPickedUpAt());

        return p;
        }


    private PrescriptionItem rebuildItem(PrescriptionEntity e) {
        return new PrescriptionItem(
                e.getDrugName(),
                e.getDin(),
                e.getStrength(),
                e.getAdministrationMethod(),
                e.getFrequency(),
                e.getInstructions(),
                e.getQuantity(),
                e.getRefillType(),
                e.getRefillCount()
        );
    }

    private Patient rebuildPatient(String patientId) {
        return patientRepo.findById(patientId)
                .map(pe -> new Patient(
                        new HealthId(pe.getHealthId()),
                        pe.getName(),
                        pe.getDateOfBirth(),
                        new Address(
                                pe.getStreet(),
                                pe.getCity(),
                                pe.getProvince(),
                                pe.getPostalCode()
                        ),
                        new InsuranceInfo(
                                pe.getPolicyNumber(),
                                pe.getProvider(),
                                pe.getInsuranceExpiry()
                        ),
                        pe.getGender(),
                        pe.getLanguagePreference(),
                        java.util.Collections.emptyList(),
                        java.util.Collections.emptyList()
                ))
                .orElse(null);
    }

    @Override
    public Prescription findById(PrescriptionId id) {
        return jpaRepo.findById(id.getValue())
                .map(this::buildDomain)
                .orElse(null);
    }

    @Override
    public List<Prescription> findAll() {
        return jpaRepo.findAll()
                .stream()
                .map(this::buildDomain)
                .toList();
    }

    @Override
    public String findLastId() {
        return jpaRepo.findLastId();
    }

    @Override
    public List<Prescription> findByPatient(Patient patient) {
        return jpaRepo.findByPatientId(patient.getHealthId().getValue())
                .stream()
                .map(this::buildDomain)
                .toList();
    }

    @Override
    public List<Prescription> findByDINAndPeriod(String din, ReportPeriod period) {
        return jpaRepo.findByDinAndDateBetween(
                        din,
                        period.getStartDate(),
                        period.getEndDate()
                )
                .stream()
                .map(this::buildDomain)
                .toList();
    }
}
