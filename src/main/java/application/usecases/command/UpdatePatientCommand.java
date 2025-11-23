package application.usecases.command;

import java.time.LocalDate;
import java.util.List;
public class UpdatePatientCommand {
    public final String healthId;

    public final String street;
    public final String city;
    public final String province;
    public final String postalCode;

    public final String policyNumber;
    public final String provider;
    public final LocalDate expiryDate;

    public final String gender;
    public final String languagePreference;

    public final List<String> allergies;
    public final List<String> currentMedications;

    public UpdatePatientCommand(
            String healthId,
            String street,
            String city,
            String province,
            String postalCode,
            String policyNumber,
            String provider,
            LocalDate expiryDate,
            String gender,
            String languagePreference,
            List<String> allergies,
            List<String> currentMedications
    ) {
        this.healthId = healthId;
        this.street = street;
        this.city = city;
        this.province = province;
        this.postalCode = postalCode;
        this.policyNumber = policyNumber;
        this.provider = provider;
        this.expiryDate = expiryDate;
        this.gender = gender;
        this.languagePreference = languagePreference;
        this.allergies = allergies;
        this.currentMedications = currentMedications;
    }
}
