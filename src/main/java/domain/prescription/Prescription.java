package domain.prescription;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import domain.patient.Patient;

public class Prescription {

    private final PrescriptionId id;
    private final LocalDate date;
    private PrescriptionStatus status;
    private final List<PrescriptionItem> items;
    private final Patient patient;
    private final String prescriberId;
    private String lotNumber;
    private LocalDate expiryDate;
    private String pickupNotes;
    private LocalDate pickedUpAt;

    public Prescription(
            PrescriptionId id,
            LocalDate date,
            List<PrescriptionItem> items,
            String prescriberId,
            Patient patient
    ) {
        this.id = Objects.requireNonNull(id);
        this.date = Objects.requireNonNull(date);
        this.items = Objects.requireNonNull(items);
        this.prescriberId = Objects.requireNonNull(prescriberId);
        this.patient = Objects.requireNonNull(patient);
        this.status = PrescriptionStatus.NEW;
    }

    public Prescription(
            PrescriptionId id,
            LocalDate date,
            List<PrescriptionItem> items,
            String prescriberId,
            Patient patient,
            PrescriptionStatus status
    ) {
        this.id = id;
        this.date = date;
        this.items = items;
        this.prescriberId = prescriberId;
        this.patient = patient;
        this.status = status != null ? status : PrescriptionStatus.NEW;
    }

    public PrescriptionId getId() { return id; }
    public LocalDate getDate() { return date; }
    public PrescriptionStatus getStatus() { return status; }
    public List<PrescriptionItem> getItems() { return items; }
    public Patient getPatient() { return patient; }
    public String getPrescriberId() { return prescriberId; }

    public String getLotNumber() { return lotNumber; }
    public LocalDate getExpiryDate() { return expiryDate; }

    public String getPickupNotes() { return pickupNotes; }
    public LocalDate getPickedUpAt() { return pickedUpAt; }

    public void setLotNumber(String lotNumber) {
        this.lotNumber = lotNumber;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }

    public void setPickupNotes(String pickupNotes) {
        this.pickupNotes = pickupNotes;
    }

    public void setPickedUpAt(LocalDate pickedUpAt) {
        this.pickedUpAt = pickedUpAt;
    }

    public void markPrepared(String lotNumber, LocalDate expiryDate) {
        if (status != PrescriptionStatus.NEW)
            throw new IllegalStateException("Already prepared.");

        this.lotNumber = lotNumber;
        this.expiryDate = expiryDate;
        this.status = PrescriptionStatus.PREPARED;
    }

    public void verify() {
        if (status != PrescriptionStatus.PREPARED)
            throw new IllegalStateException("Must be prepared first.");
        this.status = PrescriptionStatus.VERIFIED;
    }

    public void markPickedUp(String notes) {
        if (status != PrescriptionStatus.VERIFIED)
            throw new IllegalStateException("Must be verified first.");

        this.pickupNotes = notes;
        this.pickedUpAt = LocalDate.now();
        this.status = PrescriptionStatus.PICKED_UP;
    }
}
