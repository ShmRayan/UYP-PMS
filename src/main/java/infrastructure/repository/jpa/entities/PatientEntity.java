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

    protected PatientEntity() {} // requis par JPA

    public PatientEntity(String healthId, String name, LocalDate dateOfBirth) {
        this.healthId = healthId;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
    }

    // Getters & Setters
    public String getHealthId() { return healthId; }
    public String getName() { return name; }
    public LocalDate getDateOfBirth() { return dateOfBirth; }

    public void setHealthId(String healthId) { this.healthId = healthId; }
    public void setName(String name) { this.name = name; }
    public void setDateOfBirth(LocalDate dateOfBirth) { this.dateOfBirth = dateOfBirth; }
}
