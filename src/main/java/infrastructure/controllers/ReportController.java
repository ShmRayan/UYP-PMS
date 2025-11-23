package infrastructure.controllers;

import application.usecases.command.GenerateDrugReportCommand;
import application.usecases.interfaces.GenerateDrugReportUseCase;
import domain.prescription.PrescriptionRepository;
import domain.reporting.ReportPeriod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Controller
@RequestMapping("/reports")
public class ReportController {

    private final GenerateDrugReportUseCase reportUseCase;
    private final PrescriptionRepository prescriptionRepo;

    public ReportController(GenerateDrugReportUseCase reportUseCase,
                            PrescriptionRepository prescriptionRepo) {
        this.reportUseCase = reportUseCase;
        this.prescriptionRepo = prescriptionRepo;
    }

    @GetMapping("/generate")
    public String showForm(Model model) {
        model.addAttribute("command", new GenerateDrugReportCommand("", "", ""));
        return "report";
    }

    @PostMapping("/generate")
    public String generate(@ModelAttribute GenerateDrugReportCommand command, Model model) {

        var report = reportUseCase.execute(command);
        var list = prescriptionRepo.findByDINAndPeriod(
                command.din,
                new ReportPeriod(LocalDate.parse(command.startDate),
                                 LocalDate.parse(command.endDate))
        );

        model.addAttribute("report", report);
        model.addAttribute("prescriptions", list);

        return "report_result";
    }
}
