package infrastructure.controllers;

import application.usecases.command.RegisterPatientCommand;
import application.usecases.command.UpdatePatientCommand;
import application.usecases.interfaces.RegisterPatientUseCase;
import application.usecases.interfaces.UpdatePatientUseCase;

import domain.patient.*;
import domain.security.AuthorizationService;
import domain.agent.PharmacyAgent;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/patients")
@SessionAttributes("user")
public class PatientController {

    private final PatientRepository patientRepository;
    private final RegisterPatientUseCase registerPatientUseCase;
    private final UpdatePatientUseCase updatePatientUseCase;
    private final AuthorizationService authorizationService;

    public PatientController(
            PatientRepository patientRepository,
            RegisterPatientUseCase registerPatientUseCase,
            UpdatePatientUseCase updatePatientUseCase,
            AuthorizationService authorizationService
    ) {
        this.patientRepository = patientRepository;
        this.registerPatientUseCase = registerPatientUseCase;
        this.updatePatientUseCase = updatePatientUseCase;
        this.authorizationService = authorizationService;
    }

    @GetMapping("")
    public String listPatients(@RequestParam(required = false) String search,
                               @SessionAttribute("user") PharmacyAgent user,
                               Model model) {

        if (!authorizationService.hasPermission(user, "registerPatient"))
            return "error-unauthorized";

        List<Patient> patients = patientRepository.findAll();

        if (search != null && !search.isBlank()) {
            String s = search.toLowerCase();
            patients = patients.stream()
                    .filter(p ->
                            p.getName().toLowerCase().contains(s) ||
                            p.getHealthId().getValue().toLowerCase().contains(s)
                    )
                    .toList();
        }

        model.addAttribute("patients", patients);
        model.addAttribute("search", search);
        model.addAttribute("user", user);

        return "patients_list";
    }

    @GetMapping("/register")
    public String showRegisterForm(@SessionAttribute("user") PharmacyAgent user,
                                   Model model) {

        if (!authorizationService.hasPermission(user, "registerPatient"))
            return "error-unauthorized";

        model.addAttribute("user", user);
        return "patient_register";
    }

    @PostMapping("/register")
    public String register(@SessionAttribute("user") PharmacyAgent user,
                        @RequestParam String healthId,
                        @RequestParam String name,
                        @RequestParam String dateOfBirth,
                        @RequestParam String street,
                        @RequestParam String city,
                        @RequestParam String province,
                        @RequestParam String postalCode,
                        @RequestParam String policyNumber,
                        @RequestParam String provider,
                        @RequestParam String expiryDate,
                        @RequestParam String gender,
                        @RequestParam String languagePreference,
                        @RequestParam(required = false) String allergies,
                        @RequestParam(required = false) String currentMedications,
                        Model model) {

        try {

            List<String> allergyList = (allergies == null || allergies.isBlank())
                    ? List.of()
                    : List.of(allergies.split(","));

            List<String> medList = (currentMedications == null || currentMedications.isBlank())
                    ? List.of()
                    : List.of(currentMedications.split(","));


            RegisterPatientCommand command = new RegisterPatientCommand(
                    healthId,
                    name,
                    LocalDate.parse(dateOfBirth),
                    street, city, province, postalCode,
                    policyNumber, provider, LocalDate.parse(expiryDate),
                    gender, languagePreference,
                    allergyList,
                    medList
            );

            registerPatientUseCase.execute(command);

            return "redirect:/patients?success=registered";

        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "patient_register";
        }
    }


    @GetMapping("/update/{healthId}")
    public String showUpdateForm(@PathVariable String healthId,
                                @SessionAttribute("user") PharmacyAgent user,
                                Model model) {

        if (!authorizationService.hasPermission(user, "updatePatient"))
            return "error-unauthorized";

        Patient patient = patientRepository.findByHealthId(new HealthId(healthId));

        String allergyList = patient.getAllergies().stream()
                .map(Allergy::getName)
                .collect(Collectors.joining(","));

        String medicationList = patient.getCurrentMedications().stream()
                .map(Medication::getName)
                .collect(Collectors.joining(","));

        model.addAttribute("patient", patient);
        model.addAttribute("allergyList", allergyList);
        model.addAttribute("medicationList", medicationList);
        model.addAttribute("user", user);

        return "patient_update";
    }

    @PostMapping("/update")
    public String update(@SessionAttribute("user") PharmacyAgent user,
                        @RequestParam String healthId,
                        @RequestParam String street,
                        @RequestParam String city,
                        @RequestParam String province,
                        @RequestParam String postalCode,
                        @RequestParam String policyNumber,
                        @RequestParam String provider,
                        @RequestParam String expiryDate,
                        @RequestParam String gender,
                        @RequestParam String languagePreference,
                        @RequestParam(required = false) String allergies,
                        @RequestParam(required = false) String currentMedications,
                        Model model) {

        if (!authorizationService.hasPermission(user, "updatePatient"))
            return "error-unauthorized";

        try {
            List<String> allergyList = (allergies == null || allergies.isBlank())
                    ? List.of()
                    : List.of(allergies.split(","));

            List<String> medsList = (currentMedications == null || currentMedications.isBlank())
                    ? List.of()
                    : List.of(currentMedications.split(","));

            UpdatePatientCommand command = new UpdatePatientCommand(
                    healthId,
                    street,
                    city,
                    province,
                    postalCode,
                    policyNumber,
                    provider,
                    LocalDate.parse(expiryDate),
                    gender,
                    languagePreference,
                    allergyList,
                    medsList
            );

            updatePatientUseCase.execute(command);

            return "redirect:/patients?success=updated";

        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "patient_update";
        }
    }

}