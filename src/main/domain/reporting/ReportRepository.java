package domain.reporting;

public interface ReportRepository {
    void save(DrugReport report);
    DrugReport findById(ReportId id);
}
