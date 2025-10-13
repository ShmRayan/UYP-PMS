package domain.patient;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;


public class Patient {
    private final HealthId healthId;
    private final String name;
    private final LocalDate dateOfBirth;
    private Address address;
    private InsuranceInfo insuranceInfo;
    private final List<Allergy> allergies;

    public Patient(HealthId healthId, String name, LocalDate dateOfBirth,
        Address address, InsuranceInfo insuranceInfo, List<Allergy> allergies) {
        this.healthId = Objects.requireNonNull(healthId);
        this.name = Objects.requireNonNull(name);
        this.dateOfBirth = Objects.requireNonNull(dateOfBirth);
        this.address = Objects.requireNonNull(address);
        this.insuranceInfo = Objects.requireNonNull(insuranceInfo);
        this.allergies = new ArrayList<>(allergies != null ? allergies : List.of());
    }

    public HealthId getHealthId() { return healthId; }
    public String getName() { return name; }
    public LocalDate getDateOfBirth() { return dateOfBirth; }
    public Address getAddress() { return address; }
    public InsuranceInfo getInsuranceInfo() { return insuranceInfo; }
    public List<Allergy> getAllergies() { return Collections.unmodifiableList(allergies); }

    /** Commande métier — Met à jour l'adresse et l'assurance */
    public void updateInfo(Address newAddress, InsuranceInfo newInsurance) {
        this.address = Objects.requireNonNull(newAddress);
        this.insuranceInfo = Objects.requireNonNull(newInsurance);
    }

    /** Commandes complémentaires */
    public void addAllergy(Allergy allergy) { allergies.add(Objects.requireNonNull(allergy)); }
    public void removeAllergy(Allergy allergy) { allergies.remove(allergy); }

    @Override
    public String toString() {
        return String.format("Patient[%s, %s, born %s]", name, healthId, dateOfBirth);
    }
}
