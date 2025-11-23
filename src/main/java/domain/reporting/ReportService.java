package domain.reporting;

import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;

import domain.prescription.Prescription;
import domain.prescription.PrescriptionRepository;

@Service
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
                .filter(item -> item.getDin().equals(din))
                .mapToInt(item -> item.getQuantity()) 
                .sum();

       
        DrugReport report = new DrugReport(
                din,
                period.toString(), 
                totalPrescriptions,
                totalQuantity
        );

        reportRepository.save(report);
        return report;
    }
}
