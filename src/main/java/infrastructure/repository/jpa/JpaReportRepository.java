package infrastructure.repository.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import domain.reporting.DrugReport;
import domain.reporting.ReportId;
import domain.reporting.ReportRepository;

@Repository
public class JpaReportRepository implements ReportRepository {

    private final SpringDataReportRepository jpaRepo;

    @Autowired
    public JpaReportRepository(SpringDataReportRepository jpaRepo) {
        this.jpaRepo = jpaRepo;
    }

    @Override
    public void save(DrugReport report) {
        jpaRepo.save(report);
    }

    @Override
    public DrugReport findById(ReportId id) {
        return jpaRepo.findById(id.getValue()).orElse(null);
    }
}
