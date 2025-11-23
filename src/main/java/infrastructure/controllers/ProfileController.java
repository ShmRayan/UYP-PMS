package infrastructure.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import application.usecases.command.UpdateAgentCommand;
import application.usecases.interfaces.UpdateAgentUseCase;
import domain.agent.AgentId;
import domain.agent.AgentRepository;
import domain.agent.PharmacyAgent;

@Controller
@SessionAttributes("user")
public class ProfileController {

    private final AgentRepository agentRepository;
    private final UpdateAgentUseCase updateUseCase;

    public ProfileController(AgentRepository agentRepository,
                             UpdateAgentUseCase updateUseCase) {
        this.agentRepository = agentRepository;
        this.updateUseCase = updateUseCase;
    }

    @GetMapping("/profile")
    public String editProfile(
            @SessionAttribute("user") PharmacyAgent user,
            Model model) {

        // L'admin se modifie lui-même
        PharmacyAgent agent = agentRepository.findById(new AgentId(user.getId()));

        model.addAttribute("agent", agent);
        model.addAttribute("isAdmin", true); // forcé
        model.addAttribute("user", user);

        return "admin_profile_update";
    }

    @PostMapping("/profile/update")
    public String updateProfile(
            @SessionAttribute("user") PharmacyAgent user,
            Model model,
            @RequestParam String name,
            @RequestParam String email,
            @RequestParam(required = false) String newPassword) {

        UpdateAgentCommand command = new UpdateAgentCommand(
                user.getId(),
                name,
                email,
                newPassword,
                "ADMIN"
        );

        updateUseCase.execute(command);

        PharmacyAgent updated = agentRepository.findById(new AgentId(user.getId()));
        model.addAttribute("user", updated);

        return "redirect:/profile?success=1";
    }

}
