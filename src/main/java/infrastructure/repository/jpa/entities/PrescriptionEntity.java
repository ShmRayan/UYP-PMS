package infrastructure.repository.jpa.entities;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "prescriptions")
public class PrescriptionEntity {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "patient_id")
    private String patientId;

    @Column(name = "din")
    private String din;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "date")
    private LocalDate date;

    protected PrescriptionEntity() {} // Obligatoire pour JPA

    public PrescriptionEntity(String id, String patientId, String din, int quantity, LocalDate date) {
        this.id = id;
        this.patientId = patientId;
        this.din = din;
        this.quantity = quantity;
        this.date = date;
    }

    public String getId() { return id; }
    public String getPatientId() { return patientId; }
    public String getDin() { return din; }
    public int getQuantity() { return quantity; }
    public LocalDate getDate() { return date; }
}
