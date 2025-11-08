package infrastructure.repository.jpa;

import infrastructure.repository.jpa.entities.PrescriptionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface SpringDataPrescriptionRepository extends JpaRepository<PrescriptionEntity, String> {
    List<PrescriptionEntity> findByPatientId(String patientId);
    List<PrescriptionEntity> findByDinAndDateBetween(String din, LocalDate start, LocalDate end);
}
