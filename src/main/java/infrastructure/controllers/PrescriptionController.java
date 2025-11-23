package infrastructure.controllers;

import application.usecases.command.CreatePrescriptionCommand;
import application.usecases.interfaces.CreatePrescriptionUseCase;

import domain.agent.PharmacyAgent;
import domain.patient.PatientRepository;
import domain.prescription.Prescription;
import domain.prescription.PrescriptionId;
import domain.prescription.PrescriptionRepository;

import domain.security.AuthorizationService;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/prescriptions")
@SessionAttributes("user")
public class PrescriptionController {

    private final CreatePrescriptionUseCase createPrescriptionUseCase;
    private final AuthorizationService auth;
    private final PatientRepository patientRepo;
    private final PrescriptionRepository prescriptionRepo;

    public PrescriptionController(
            CreatePrescriptionUseCase createPrescriptionUseCase,
            AuthorizationService auth,
            PatientRepository patientRepo,
            PrescriptionRepository prescriptionRepo
    ) {
        this.createPrescriptionUseCase = createPrescriptionUseCase;
        this.auth = auth;
        this.patientRepo = patientRepo;
        this.prescriptionRepo = prescriptionRepo;
    }

    @GetMapping("")
    public String list(Model model) {
        List<Prescription> list = prescriptionRepo.findAll();
        model.addAttribute("prescriptions", list);
        return "prescriptions_list";
    }

    @GetMapping("/create")
    public String showForm(@SessionAttribute("user") PharmacyAgent user, Model model) {

        if (!auth.hasPermission(user, "createPrescription"))
            return "error-unauthorized";

        model.addAttribute("patients", patientRepo.findAll());
        return "prescription_create";
    }

    @PostMapping("/create")
    public String create(
            @RequestParam String patientId,
            @RequestParam String prescriberId,
            @RequestParam String drugName,
            @RequestParam String din,
            @RequestParam String strength,
            @RequestParam String adminMethod,
            @RequestParam String frequency,
            @RequestParam String instructions,
            @RequestParam int quantity,
            @RequestParam String refillType,
            @RequestParam(defaultValue = "0") int refillCount,
            Model model,
            @SessionAttribute("user") PharmacyAgent user
    ) {
        if (!auth.hasPermission(user, "createPrescription"))
            return "error-unauthorized";

        try {
            CreatePrescriptionCommand cmd = new CreatePrescriptionCommand(
                    patientId, prescriberId, drugName, din, strength,
                    adminMethod, frequency, instructions, quantity,
                    refillType, refillCount
            );

            createPrescriptionUseCase.execute(cmd);

            return "redirect:/prescriptions";

        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
            model.addAttribute("patients", patientRepo.findAll());
            return "prescription_create";
        }
    }

    @PostMapping("/{id}/prepare")
    public String prepare(
            @PathVariable String id,
            @RequestParam String lotNumber,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate expiryDate
    ) {
        Prescription p = prescriptionRepo.findById(new PrescriptionId(id));
        if (p == null) return "error";

        p.markPrepared(lotNumber, expiryDate);
        prescriptionRepo.save(p);

        return "redirect:/prescriptions";
    }

    @PostMapping("/{id}/verify")
    public String verify(@PathVariable String id) {

        Prescription p = prescriptionRepo.findById(new PrescriptionId(id));
        if (p == null) return "error";

        p.verify();
        prescriptionRepo.save(p);

        return "redirect:/prescriptions";
    }

    @PostMapping("/{id}/pickup")
    public String pickup(
            @PathVariable String id,
            @RequestParam String pickupNotes
    ) {

        Prescription p = prescriptionRepo.findById(new PrescriptionId(id));
        if (p == null) return "error";

        p.markPickedUp(pickupNotes);
        prescriptionRepo.save(p);

        return "redirect:/prescriptions";
    }
}
