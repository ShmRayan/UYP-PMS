package infrastructure.controllers;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import application.usecases.command.CreatePrescriptionCommand;
import domain.agent.PharmacyAgent;
import domain.patient.Address;
import domain.patient.HealthId;
import domain.patient.InsuranceInfo;
import domain.patient.Patient;
import domain.prescription.Prescription;
import domain.prescription.PrescriptionId;
import domain.prescription.PrescriptionItem;
import domain.prescription.PrescriptionRepository;
import domain.security.AuthorizationService;

@Controller
@RequestMapping("/prescriptions")
@SessionAttributes("user")
public class PrescriptionController {

    private final PrescriptionRepository prescriptionRepository;
    private final AuthorizationService authorizationService;

    public PrescriptionController(PrescriptionRepository prescriptionRepository,
                                  AuthorizationService authorizationService) {
        this.prescriptionRepository = prescriptionRepository;
        this.authorizationService = authorizationService;
    }


    @GetMapping("/create")
    public String showCreateForm(Model model, @SessionAttribute("user") PharmacyAgent user) {

        if (!authorizationService.hasPermission(user, "createPrescription")) {
            model.addAttribute("errorMessage", "⛔ Vous n’avez pas la permission de créer une ordonnance.");
            return "error-unauthorized";
        }

        model.addAttribute("command", new CreatePrescriptionCommand("", "", "", "", 1));
        return "create_prescription";
    }

    @PostMapping("/create")
    public String createPrescription(@ModelAttribute("command") CreatePrescriptionCommand command,
                                     @SessionAttribute("user") PharmacyAgent user,
                                     Model model) {

        if (!authorizationService.hasPermission(user, "createPrescription")) {
            model.addAttribute("errorMessage", "⛔ Accès refusé : vous n’avez pas les droits nécessaires.");
            return "error-unauthorized";
        }

        try {
            PrescriptionItem item = new PrescriptionItem(
                    command.instructions,
                    command.din,
                    "N/A",
                    "voir instructions",
                    command.quantity
            );

            HealthId healthId = new HealthId(command.patientId);
            Address dummyAddress = new Address("Adresse inconnue", "Ville", "ON", "K1K1K1");
            InsuranceInfo dummyInsurance = new InsuranceInfo("0000", "Aucune", LocalDate.now().plusYears(1));

            Patient patient = new Patient(
                    healthId,
                    "Patient inconnu",
                    LocalDate.now(),
                    dummyAddress,
                    dummyInsurance,
                    List.of()
            );

            Prescription prescription = new Prescription(
                    new PrescriptionId(),
                    LocalDate.now(),
                    List.of(item),
                    user,
                    patient
            );

            prescriptionRepository.save(prescription);

            model.addAttribute("successMessage", "✅ Prescription créée avec succès !");
            return "redirect:/dashboard";

        } catch (Exception e) {
            model.addAttribute("errorMessage", "Erreur lors de la création : " + e.getMessage());
            return "create_prescription";
        }
    }
}
