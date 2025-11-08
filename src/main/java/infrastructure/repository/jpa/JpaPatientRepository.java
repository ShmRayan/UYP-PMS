package infrastructure.repository.jpa;

import domain.patient.*;
import infrastructure.repository.jpa.entities.PatientEntity;
import org.springframework.stereotype.Repository;
import java.util.Optional;

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
                patient.getDateOfBirth()
        );
        jpaRepo.save(entity);
    }

    @Override
    public Patient findByHealthId(HealthId id) {
        Optional<PatientEntity> entity = jpaRepo.findById(id.getValue());
        return entity.map(e ->
                new Patient(
                        new HealthId(e.getHealthId()),
                        e.getName(),
                        e.getDateOfBirth(),
                        null, // tu peux mapper Address/InsuranceInfo si tu veux
                        null,
                        java.util.List.of()
                )
        ).orElse(null);
    }
}
