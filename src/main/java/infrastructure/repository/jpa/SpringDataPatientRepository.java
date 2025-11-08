package infrastructure.repository.jpa;

import infrastructure.repository.jpa.entities.PatientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringDataPatientRepository extends JpaRepository<PatientEntity, String> {
}
