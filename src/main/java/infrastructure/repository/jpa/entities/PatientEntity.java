package infrastructure.repository.jpa.entities;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "patients")
public class PatientEntity {

    @Id
    private String healthId;

    private String name;
    private LocalDate dateOfBirth;

    // Address
    private String street;
    private String city;
    private String province;
    private String postalCode;

    // Insurance
    private String policyNumber;
    private String provider;
    private LocalDate insuranceExpiry;

    // NEW fields
    private String gender;
    private String languagePreference;

    @Lob
    private String allergies;

    @Lob
    private String currentMedications;

    protected PatientEntity() {}

    public PatientEntity(
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
            String allergies,
            String currentMedications
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

    public String getHealthId() {
        return healthId;
    }

    public String getName() {
        return name;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }

    public String getProvince() {
        return province;
    }

    public String getPostalCode() {
        return postalCode;
    }

    // --- Insurance ---
    public String getPolicyNumber() {
        return policyNumber;
    }

    public String getProvider() {
        return provider;
    }

    public LocalDate getInsuranceExpiry() {
        return insuranceExpiry;
    }

    public String getGender() {
        return gender;
    }

    public String getLanguagePreference() {
        return languagePreference;
    }

    public String getAllergies() {
        return allergies;
    }

    public String getCurrentMedications() {
        return currentMedications;
    }

}
