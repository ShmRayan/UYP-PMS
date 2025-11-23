package application.usecases.command;

import java.time.LocalDate;
import java.util.List;

public class RegisterPatientCommand {

    public final String healthId;
    public final String name;
    public final LocalDate dateOfBirth;

    public final String street;
    public final String city;
    public final String province;
    public final String postalCode;

    public final String policyNumber;
    public final String provider;
    public final LocalDate insuranceExpiry;

    public final String gender;
    public final String languagePreference;

    public final List<String> allergies;
    public final List<String> currentMedications;

    public RegisterPatientCommand(
            String healthId,
            String name,
            LocalDate dateOfBirth,
            String street,
            String city,
            String province,
            String postalCode,
            String policyNumber,
            String provider,
            LocalDate insuranceExpiry,
            String gender,
            String languagePreference,
            List<String> allergies,
            List<String> currentMedications
    ) {
        this.healthId = healthId;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.street = street;
        this.city = city;
        this.province = province;
        this.postalCode = postalCode;
        this.policyNumber = policyNumber;
        this.provider = provider;
        this.insuranceExpiry = insuranceExpiry;
        this.gender = gender;
        this.languagePreference = languagePreference;
        this.allergies = allergies;
        this.currentMedications = currentMedications;
    }
}
