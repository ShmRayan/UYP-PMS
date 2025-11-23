package infrastructure.repository.jpa;

import domain.patient.*;
import infrastructure.repository.jpa.entities.PatientEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JpaPatientRepository implements PatientRepository {

    private final SpringDataPatientRepository jpaRepo;

    public JpaPatientRepository(SpringDataPatientRepository jpaRepo) {
        this.jpaRepo = jpaRepo;
    }

    @Override
    public void save(Patient patient) {

        PatientEntity entity = new PatientEntity(
                patient.getHealthId().getValue(),
                patient.getName(),
                patient.getDateOfBirth(),
                patient.getAddress().getStreet(),
                patient.getAddress().getCity(),
                patient.getAddress().getProvince(),
                patient.getAddress().getPostalCode(),
                patient.getInsuranceInfo().getPolicyNumber(),
                patient.getInsuranceInfo().getProvider(),
                patient.getInsuranceInfo().getExpiryDate(),
                patient.getGender(),
                patient.getLanguagePreference(),
                String.join(",", patient.getAllergies().stream().map(Allergy::getName).toList()),
                String.join(",", patient.getCurrentMedications().stream().map(Medication::getName).toList())
        );

        jpaRepo.save(entity);
    }

    @Override
    public Patient findByHealthId(HealthId id) {
        return jpaRepo.findById(id.getValue())
                .map(this::toDomain)
                .orElse(null);
    }
    
    @Override
    public List<Patient> findAll() {
        return jpaRepo.findAll()
                .stream()
                .map(this::toDomain)
                .toList();
    }

    private Patient toDomain(PatientEntity e) {

        Address address = new Address(e.getStreet(), e.getCity(), e.getProvince(), e.getPostalCode());

        InsuranceInfo insurance = new InsuranceInfo(
                e.getPolicyNumber(),
                e.getProvider(),
                e.getInsuranceExpiry()
        );

        List<Allergy> allergies = List.of(e.getAllergies().split(","))
                                      .stream().map(Allergy::new).toList();

        List<Medication> meds = List.of(e.getCurrentMedications().split(","))
                                    .stream().map(Medication::new).toList();

        return new Patient(
                new HealthId(e.getHealthId()),
                e.getName(),
                e.getDateOfBirth(),
                address,
                insurance,
                e.getGender(),
                e.getLanguagePreference(),
                allergies,
                meds
        );
    }
}
