package domain.reporting;

import domain.prescription.Prescription;
import domain.prescription.PrescriptionRepository;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class ReportService {
    private final ReportRepository reportRepository;
    private final PrescriptionRepository prescriptionRepository;

    public ReportService(ReportRepository reportRepository, PrescriptionRepository prescriptionRepository) {
        this.reportRepository = Objects.requireNonNull(reportRepository);
        this.prescriptionRepository = Objects.requireNonNull(prescriptionRepository);
    }

    public DrugReport generateReport(String din, ReportPeriod period) {
        List<Prescription> prescriptions = prescriptionRepository.findByDINAndPeriod(din, period);

        int totalPrescriptions = prescriptions.size();
        int totalQuantity = prescriptions.stream()
                .flatMap(p -> p.getItems().stream())
                .filter(item -> item.getDIN().equals(din))
                .mapToInt(item -> 1) // tu pourrais remplacer par item.getQuantity() si dispo
                .sum();

        DrugReport report = new DrugReport(
                new ReportId(UUID.randomUUID().toString()),
                din,
                period,
                totalPrescriptions,
                totalQuantity
        );

        reportRepository.save(report);
        return report;
    }
}
