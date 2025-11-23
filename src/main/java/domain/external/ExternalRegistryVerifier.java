package domain.external;

public class ExternalRegistryVerifier {

    /**
     * Simule une vérification externe (CPSO registry).
     * Règle simple : tout ID commençant par "AGT" est considéré valide.
     */
    public static boolean isValidPrescriber(String agentId) {
        if (agentId == null) return false;
        return agentId.startsWith("AGT");
    }
}
