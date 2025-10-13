package application.usecases.command;

import java.time.LocalDate;
import java.util.List;

import domain.patient.Allergy;

public class RegisterPatientCommand {
    public String healthId;
    public String name;
    public LocalDate dateOfBirth;
    public String street;
    public String city;
    public String province;
    public String postalCode;
    public String policyNumber;
    public String provider;
    public LocalDate expiryDate;
    public List<Allergy> allergies;

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
            LocalDate expiryDate,
            List<Allergy> allergies
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
        this.expiryDate = expiryDate;
        this.allergies = allergies;
    }
}
