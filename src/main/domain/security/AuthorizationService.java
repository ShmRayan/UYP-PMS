package domain.security;

import domain.agent.PharmacyAgent;

public class AuthorizationService {

    public boolean hasPermission(PharmacyAgent user, String action) {
        // Exemple simple de logique d'autorisation
        if (user == null || user.getRole() == null) return false;

        return switch (user.getRole()) {
            case PHARMACIST -> true;
            case ASSISTANT -> !action.equalsIgnoreCase("registerAgent")
                && !action.equalsIgnoreCase("unregisterAgent");
            default -> false;
        }; // un pharmacien peut tout faire
    }
}
