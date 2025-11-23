package infrastructure.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import application.usecases.command.RegisterAgentCommand;
import application.usecases.command.UnregisterAgentCommand;
import application.usecases.command.UpdateAgentCommand;
import application.usecases.interfaces.RegisterAgentUseCase;
import application.usecases.interfaces.UnregisterAgentUseCase;
import application.usecases.interfaces.UpdateAgentUseCase;
import domain.agent.AgentId;
import domain.agent.AgentRepository;
import domain.agent.AgentRole;
import domain.agent.PharmacyAgent;
import domain.security.AuthorizationService;

@Controller
@RequestMapping("/agents")
@SessionAttributes("user")
public class AgentController {

    private final AgentRepository agentRepository;
    private final AuthorizationService authorizationService;
    private final RegisterAgentUseCase registerUseCase;
    private final UpdateAgentUseCase updateUseCase;
    private final UnregisterAgentUseCase unregisterUseCase;

    public AgentController(
            AgentRepository agentRepository,
            AuthorizationService authorizationService,
            RegisterAgentUseCase registerUseCase,
            UpdateAgentUseCase updateUseCase,
            UnregisterAgentUseCase unregisterUseCase) {

        this.agentRepository = agentRepository;
        this.authorizationService = authorizationService;
        this.registerUseCase = registerUseCase;
        this.updateUseCase = updateUseCase;
        this.unregisterUseCase = unregisterUseCase;
    }

    @GetMapping("")
    public String listAgents(@RequestParam(value = "search", required = false) String search,
                            @SessionAttribute("user") PharmacyAgent user,
                            Model model) {

        List<PharmacyAgent> all = agentRepository.findAll()
            .stream()
            .filter(agent -> agent.getRole() != AgentRole.ADMIN)
            .toList();

        if (search != null && !search.isEmpty()) {
            all = all.stream()
                    .filter(a -> a.getName().toLowerCase().contains(search.toLowerCase()))
                    .toList();
        }

        model.addAttribute("search", search);   
        model.addAttribute("agents", all);

        return "agents_list";
    }

    @GetMapping("/register")
    public String registerForm(
            @SessionAttribute("user") PharmacyAgent user,
            Model model) {

        if (!authorizationService.hasPermission(user, "registerAgent")) {
            return "error-unauthorized";
        }

        model.addAttribute("command", new RegisterAgentCommand("", "", ""));
        model.addAttribute("user", user);

        return "register_agent";
    }

    @PostMapping("/register")
    public String register(
            @ModelAttribute("command") RegisterAgentCommand command,
            @SessionAttribute("user") PharmacyAgent user) {

        if (!authorizationService.hasPermission(user, "registerAgent")) {
            return "error-unauthorized";
        }

        if (command.role.equalsIgnoreCase("ADMIN")) {
            throw new IllegalArgumentException("Impossible de créer un administrateur.");
        }

        registerUseCase.execute(command);
        return "redirect:/agents";
    }
   @GetMapping("/update/{id}")
    public String updateForm(@PathVariable String id,
                            @SessionAttribute("user") PharmacyAgent user,
                            Model model) {

        if (!authorizationService.hasPermission(user, "updateAgent")) {
            return "error-unauthorized";
        }

        PharmacyAgent agent = agentRepository.findById(new AgentId(id));

        model.addAttribute("agent", agent);
        model.addAttribute("isAdmin", agent.getRole() == AgentRole.ADMIN);

        return "update_agent";
    }


    @PostMapping("/update")
    public String update(@SessionAttribute("user") PharmacyAgent user,
                        @RequestParam String agentId,
                        @RequestParam String name,
                        @RequestParam String email,
                        @RequestParam(required = false) String role,
                        @RequestParam(required = false) String newPassword) {

        if (!authorizationService.hasPermission(user, "updateAgent")) {
            return "error-unauthorized";
        }

        PharmacyAgent agent = agentRepository.findById(new AgentId(agentId));

        if (agent.getRole() == AgentRole.ADMIN) {
            role = "ADMIN";
        }

        UpdateAgentCommand command = new UpdateAgentCommand(
                agentId,
                name,
                email,
                newPassword,
                role
        );

        updateUseCase.execute(command);

        return "redirect:/agents";
    }

    @GetMapping("/deactivate/{id}")
    public String deactivate(
            @PathVariable String id,
            @SessionAttribute("user") PharmacyAgent user) {

        if (!authorizationService.hasPermission(user, "unregisterAgent")) {
            return "error-unauthorized";
        }

        PharmacyAgent agent = agentRepository.findById(new AgentId(id));

        if (agent.getRole() == AgentRole.ADMIN) {
            return "redirect:/agents?error=admin_protected";
        }

        if (!agent.isActive()) {
            return "redirect:/agents?error=already_inactive";
        }

        unregisterUseCase.execute(new UnregisterAgentCommand(id));

        return "redirect:/agents?success=deactivated";
    }

}
