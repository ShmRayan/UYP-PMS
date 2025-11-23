package domain.patient;

import java.time.LocalDate;
import java.util.List;

public class Patient {

    private final HealthId healthId;
    private final String name;
    private final LocalDate dateOfBirth;

    private Address address;
    private InsuranceInfo insuranceInfo;

    private String gender;
    private String languagePreference;

    private List<Allergy> allergies;
    private List<Medication> currentMedications;

    public Patient(
            HealthId healthId,
            String name,
            LocalDate dateOfBirth,
            Address address,
            InsuranceInfo insuranceInfo,
            String gender,
            String languagePreference,
            List<Allergy> allergies,
            List<Medication> currentMedications
    ) {
        this.healthId = healthId;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.insuranceInfo = insuranceInfo;
        this.gender = gender;
        this.languagePreference = languagePreference;
        this.allergies = allergies;
        this.currentMedications = currentMedications;
    }

    public HealthId getHealthId() { return healthId; }
    public String getName() { return name; }
    public LocalDate getDateOfBirth() { return dateOfBirth; }
    public Address getAddress() { return address; }
    public InsuranceInfo getInsuranceInfo() { return insuranceInfo; }
    public String getGender() { return gender; }
    public String getLanguagePreference() { return languagePreference; }
    public List<Allergy> getAllergies() { return allergies; }
    public List<Medication> getCurrentMedications() { return currentMedications; }

    public void updateInfo(
            Address newAddress,
            InsuranceInfo newInsurance,
            String gender,
            String languagePreference,
            List<Allergy> allergies,
            List<Medication> meds
    ) {
        this.address = newAddress;
        this.insuranceInfo = newInsurance;
        this.gender = gender;
        this.languagePreference = languagePreference;
        this.allergies = allergies;
        this.currentMedications = meds;
    }

}
