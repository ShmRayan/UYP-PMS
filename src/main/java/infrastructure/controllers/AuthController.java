package infrastructure.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import domain.agent.PharmacyAgent;
import domain.security.AuthenticationService;
import domain.security.UserCredentials;
import jakarta.servlet.http.HttpSession;

@Controller
public class AuthController {

    private final AuthenticationService authService;

    public AuthController(AuthenticationService authService) {
        this.authService = authService;
    }

    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("credentials", new UserCredentials("", ""));
        return "login";
    }

    @PostMapping("/login")
    public String login(UserCredentials credentials, HttpSession httpSession, Model model) {
        try {
            PharmacyAgent agent = authService.authenticate(credentials);

            httpSession.setAttribute("user", agent);

            return "redirect:/dashboard";

        } catch (IllegalStateException e) {
            model.addAttribute("error", "⛔ Ce compte est désactivé !");
            model.addAttribute("credentials", new UserCredentials("", ""));
            return "login";

        } catch (Exception e) {
            model.addAttribute("error", "⛔ Adresse e-mail ou mot de passe incorrect !");
            model.addAttribute("credentials", new UserCredentials("", ""));
            return "login";
        }
    }

    @GetMapping("/dashboard")
    public String dashboard(HttpSession httpSession, Model model) {
        PharmacyAgent user = (PharmacyAgent) httpSession.getAttribute("user");

        // Si aucun utilisateur n’est connecté → retour à la page login
        if (user == null) {
            return "redirect:/login";
        }

        // Ajoute l’utilisateur au modèle pour afficher son nom dans la page
        model.addAttribute("user", user);

        // Redirection vers la vue selon le rôle
        return switch (user.getRole().name().toUpperCase()) {
            case "ADMIN" -> "dashboard_admin";
            case "PHARMACIST" -> "dashboard_pharmacist";
            case "ASSISTANT" -> "dashboard_assistant";
            default -> "redirect:/login";
        }; // Sécurité
    }

    // --- Déconnexion ---
    @GetMapping("/logout")
    public String logout(HttpSession httpSession) {
        httpSession.invalidate(); // Supprime la session
        return "redirect:/login"; // Retour à la page de connexion
    }
}
