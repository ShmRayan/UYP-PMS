package domain.external;

import java.util.List;

public class DrugDatabaseVerifier {

    // Tu peux ajouter plus de DIN si tu veux.
    private static final List<String> VALID_DINS = List.of(
            "02212345",
            "02456789",
            "00876543",
            "02345001"
    );

    /**
     * Vérifie si un DIN existe dans une base simulée.
     */
    public static boolean isValidDIN(String din) {
        return din != null && din.matches("\\d{8}") && VALID_DINS.contains(din);
    }
}
