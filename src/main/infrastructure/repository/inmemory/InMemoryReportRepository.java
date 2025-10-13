package infrastructure.repository.inmemory;

import java.util.HashMap;
import java.util.Map;

import domain.reporting.DrugReport;
import domain.reporting.ReportId;
import domain.reporting.ReportRepository;

public class InMemoryReportRepository implements ReportRepository {

    private final Map<String, DrugReport> reports = new HashMap<>();

    @Override
    public void save(DrugReport report) {
        reports.put(report.getId().getValue(), report);
    }

    @Override
    public DrugReport findById(ReportId id) {
        return reports.get(id.getValue());
    }
}
