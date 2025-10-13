package application.usecases.command;

import java.time.LocalDate;

public class UpdatePatientCommand {
    public String healthId;
    public String street;
    public String city;
    public String province;
    public String postalCode;
    public String policyNumber;
    public String provider;
    public LocalDate expiryDate;

    public UpdatePatientCommand(
            String healthId,
            String street,
            String city,
            String province,
            String postalCode,
            String policyNumber,
            String provider,
            LocalDate expiryDate
    ) {
        this.healthId = healthId;
        this.street = street;
        this.city = city;
        this.province = province;
        this.postalCode = postalCode;
        this.policyNumber = policyNumber;
        this.provider = provider;
        this.expiryDate = expiryDate;
    }
}
