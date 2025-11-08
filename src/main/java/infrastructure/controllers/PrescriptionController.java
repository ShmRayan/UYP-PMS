package infrastructure.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import application.usecases.command.CreatePrescriptionCommand;
import application.usecases.command.PreparePrescriptionFillCommand;
import application.usecases.interfaces.CreatePrescriptionUseCase;
import application.usecases.interfaces.PreparePrescriptionFillUseCase;

@Controller
@RequestMapping("/prescriptions")
public class PrescriptionController {

    private final CreatePrescriptionUseCase createUseCase;
    private final PreparePrescriptionFillUseCase prepareUseCase;

    public PrescriptionController(CreatePrescriptionUseCase createUseCase,
                                  PreparePrescriptionFillUseCase prepareUseCase) {
        this.createUseCase = createUseCase;
        this.prepareUseCase = prepareUseCase;
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("command", new CreatePrescriptionCommand("", "", "", "", 1));
        return "create_prescription"; 
    }

    @PostMapping("/create")
    public String createPrescription(@ModelAttribute CreatePrescriptionCommand command) {
        createUseCase.execute(command);
        return "redirect:/dashboard";
    }

    @PostMapping("/prepare/{id}")
    public String preparePrescription(@PathVariable String id,
                                      @RequestParam int quantity,
                                      @RequestParam String lotNumber,
                                      @RequestParam String expiryDate) {

        PreparePrescriptionFillCommand command =
                new PreparePrescriptionFillCommand(id, quantity, lotNumber, expiryDate);

        prepareUseCase.execute(command);
        return "redirect:/dashboard";
    }
}
