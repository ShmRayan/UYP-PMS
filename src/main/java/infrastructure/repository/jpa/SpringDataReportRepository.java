package infrastructure.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.reporting.DrugReport;

@Repository
public interface SpringDataReportRepository extends JpaRepository<DrugReport, String> {
}
