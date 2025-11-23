package infrastructure.repository.jpa;

import infrastructure.repository.jpa.entities.PrescriptionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface SpringDataPrescriptionRepository
        extends JpaRepository<PrescriptionEntity, String> {

    List<PrescriptionEntity> findByPatientId(String patientId);

    List<PrescriptionEntity> findByDinAndDateBetween(
            String din,
            LocalDate startDate,
            LocalDate endDate
    );
    
    @Query(value = "SELECT id FROM prescriptions ORDER BY id DESC LIMIT 1", nativeQuery = true)
    String findLastId();
}
