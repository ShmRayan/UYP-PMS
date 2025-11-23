package infrastructure.repository.jpa.entities;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "prescriptions")
public class PrescriptionEntity {

    @Id
    private String id;
    private LocalDate date;
    private String din;
    private String patientId;
    private int quantity;
    private String drugName;
    private String frequency;
    private String instructions;
    private String prescriberId;
    private int refillCount;
    private String refillType;
    private String strength;
    private String administrationMethod;
    private String status;
    private String lotNumber;
    private LocalDate expiryDate;
    private String pickupNotes;
    private LocalDate pickedUpAt;

    protected PrescriptionEntity() {}

    public PrescriptionEntity(
            String id,
            LocalDate date,
            String din,
            String patientId,
            int quantity,
            String drugName,
            String frequency,
            String instructions,
            String prescriberId,
            int refillCount,
            String refillType,
            String strength,
            String administrationMethod,
            String status,
            String lotNumber,
            LocalDate expiryDate,
            String pickupNotes,
            LocalDate pickedUpAt
    ) {
        this.id = id;
        this.date = date;
        this.din = din;
        this.patientId = patientId;
        this.quantity = quantity;
        this.drugName = drugName;
        this.frequency = frequency;
        this.instructions = instructions;
        this.prescriberId = prescriberId;
        this.refillCount = refillCount;
        this.refillType = refillType;
        this.strength = strength;
        this.administrationMethod = administrationMethod;
        this.status = status;
        this.lotNumber = lotNumber;
        this.expiryDate = expiryDate;
        this.pickupNotes = pickupNotes;
        this.pickedUpAt = pickedUpAt;
    }

    public String getId() { return id; }
    public LocalDate getDate() { return date; }
    public String getDin() { return din; }
    public String getPatientId() { return patientId; }
    public int getQuantity() { return quantity; }
    public String getDrugName() { return drugName; }
    public String getFrequency() { return frequency; }
    public String getInstructions() { return instructions; }
    public String getPrescriberId() { return prescriberId; }
    public int getRefillCount() { return refillCount; }
    public String getRefillType() { return refillType; }
    public String getStrength() { return strength; }
    public String getAdministrationMethod() { return administrationMethod; }
    public String getStatus() { return status; }

    public String getLotNumber() { return lotNumber; }
    public LocalDate getExpiryDate() { return expiryDate; }

    public String getPickupNotes() { return pickupNotes; }
    public LocalDate getPickedUpAt() { return pickedUpAt; }
}
