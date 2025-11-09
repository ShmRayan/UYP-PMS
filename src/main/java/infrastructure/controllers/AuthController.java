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

    // --- Injection du service d’authentification ---
    public AuthController(AuthenticationService authService) {
        this.authService = authService;
    }

    // --- Afficher le formulaire de connexion ---
    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("credentials", new UserCredentials("", ""));
        return "login";
    }

    // --- Traiter la connexion ---
    @PostMapping("/login")
    public String login(UserCredentials credentials, HttpSession httpSession, Model model) {
        try {
            // Authentifie l’agent
            PharmacyAgent agent = authService.authenticate(credentials);

            // Sauvegarde en session pour conserver l’état de connexion
            httpSession.setAttribute("user", agent);

            // Redirection vers le tableau de bord correspondant
            return "redirect:/dashboard";

        } catch (Exception e) {
            // En cas d’échec, renvoyer la page login avec un message d’erreur
            model.addAttribute("error", "Adresse e-mail ou mot de passe incorrect !");
            model.addAttribute("credentials", new UserCredentials("", ""));
            return "login";
        }
    }

    // --- Sélection du bon dashboard selon le rôle ---
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
        switch (user.getRole().name().toUpperCase()) {
            case "ADMIN":
                return "dashboard_admin";
            case "PHARMACIST":
                return "dashboard_pharmacist";
            case "ASSISTANT":
                return "dashboard_assistant";
            default:
                return "redirect:/login"; // Sécurité
        }
    }

    // --- Déconnexion ---
    @GetMapping("/logout")
    public String logout(HttpSession httpSession) {
        httpSession.invalidate(); // Supprime la session
        return "redirect:/login"; // Retour à la page de connexion
    }
}
