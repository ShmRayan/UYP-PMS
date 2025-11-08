package domain.prescription;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import domain.agent.PharmacyAgent;
import domain.patient.Patient;

public class Prescription {
    private final PrescriptionId id;
    private final LocalDate date;
    private PrescriptionStatus status;
    private final List<PrescriptionItem> items;
    private PharmacyAgent preparedBy;
    private final Patient patient;

    public Prescription(PrescriptionId id, LocalDate date, List<PrescriptionItem> items,
                        PharmacyAgent preparedBy, Patient patient) {
        this.id = Objects.requireNonNull(id);
        this.date = Objects.requireNonNull(date);
        this.items = new ArrayList<>(Objects.requireNonNull(items));
        this.preparedBy = preparedBy;
        this.patient = Objects.requireNonNull(patient);
        this.status = PrescriptionStatus.NEW;
    }

    public PrescriptionId getId() { return id; }
    public LocalDate getDate() { return date; }
    public PrescriptionStatus getStatus() { return status; }
    public List<PrescriptionItem> getItems() { return Collections.unmodifiableList(items); }
    public PharmacyAgent getPreparedBy() { return preparedBy; }
    public Patient getPatient() { return patient; }

    // --- Méthodes métier ---
    public void markPrepared(PharmacyAgent agent) {
        if (status != PrescriptionStatus.NEW)
            throw new IllegalStateException("Prescription already prepared or beyond.");
        this.preparedBy = Objects.requireNonNull(agent);
        this.status = PrescriptionStatus.PREPARED;
    }

    public void verify(PharmacyAgent agent) {
        if (status != PrescriptionStatus.PREPARED)
            throw new IllegalStateException("Prescription must be prepared before verification.");
        this.status = PrescriptionStatus.VERIFIED;
        this.preparedBy = Objects.requireNonNull(agent);
    }

    public void markPickedUp(Patient patient) {
        if (status != PrescriptionStatus.VERIFIED)
            throw new IllegalStateException("Prescription must be verified before pickup.");
        if (!this.patient.equals(patient))
            throw new IllegalArgumentException("Pickup must be done by the correct patient.");
        this.status = PrescriptionStatus.PICKED_UP;
    }

    @Override
    public String toString() {
        return String.format("Prescription[id=%s, patient=%s, status=%s, items=%d]",
                id, patient.getHealthId(), status, items.size());
    }
}
