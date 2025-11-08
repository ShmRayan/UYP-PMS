package application.usecases.implementation;

import application.usecases.command.GenerateDrugReportCommand;
import application.usecases.interfaces.GenerateDrugReportUseCase;
import domain.reporting.*;
import java.time.LocalDate;

import org.springframework.stereotype.Service;

@Service
public class GenerateDrugReportUseCaseImpl implements GenerateDrugReportUseCase {

    @SuppressWarnings("unused")
    private final ReportRepository reportRepository;
    private final ReportService reportService;

    public GenerateDrugReportUseCaseImpl(ReportRepository reportRepository,
                                         ReportService reportService) {
        this.reportRepository = reportRepository;
        this.reportService = reportService;
    }

    @Override
    public DrugReport execute(GenerateDrugReportCommand command) {
        LocalDate start = LocalDate.parse(command.startDate);
        LocalDate end = LocalDate.parse(command.endDate);
        ReportPeriod period = new ReportPeriod(start, end);
        return reportService.generateReport(command.din, period);
    }
}
