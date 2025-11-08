package infrastructure.controllers;

import application.usecases.command.GenerateDrugReportCommand;
import application.usecases.interfaces.GenerateDrugReportUseCase;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/reports")
public class ReportController {

    private final GenerateDrugReportUseCase reportUseCase;

    public ReportController(GenerateDrugReportUseCase reportUseCase) {
        this.reportUseCase = reportUseCase;
    }

    @GetMapping("/generate")
    public String showForm(Model model) {
        model.addAttribute("command", new GenerateDrugReportCommand("", "", ""));
        return "report";
    }

    @PostMapping("/generate")
    public String generate(@ModelAttribute GenerateDrugReportCommand command, Model model) {
        var report = reportUseCase.execute(command);
        model.addAttribute("report", report);
        return "report_result";
    }
}
