package domain.security;

import org.springframework.stereotype.Service;

import domain.agent.PharmacyAgent;

@Service
public class AuthorizationService {

    public boolean hasPermission(PharmacyAgent user, String action) {
        if (user == null || user.getRole() == null) return false;

        return switch (user.getRole()) {
            case ADMIN -> switch (action) {
                case "registerAgent", "unregisterAgent", "updateAgent" -> true;
                default -> false;
            };
            case PHARMACIST -> switch (action) {
                case "registerPatient", "updatePatient",
                "createPrescription", "preparePrescription",
                "verifyPrescription", "generateReport", "pickUpMedicine" -> true;
                default -> false;
            };
            case ASSISTANT -> switch (action) {
                case "registerPatient", "updatePatient",
                "createPrescription", "preparePrescription",
                "updateAgent" -> true; 
                default -> false;
            };
            default -> false;
        };
    }
}
