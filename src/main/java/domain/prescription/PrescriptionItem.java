package domain.prescription;

import java.util.Objects;

public final class PrescriptionItem {
    private final String drugName;
    private final String DIN;
    private final String dosage;
    private final String frequency;
    private final int quantity;

    public PrescriptionItem(String drugName, String DIN, String dosage, String frequency, int quantity) {
        this.drugName = Objects.requireNonNull(drugName);
        this.DIN = Objects.requireNonNull(DIN);
        this.dosage = Objects.requireNonNull(dosage);
        this.frequency = Objects.requireNonNull(frequency);
        this.quantity = quantity;
    }

    public String getDrugName() { return drugName; }
    public String getDIN() { return DIN; }
    public String getDosage() { return dosage; }
    public String getFrequency() { return frequency; }
    public int getQuantity() { return quantity; }

    @Override
    public String toString() {
        return String.format("%s (%s) - %s, %s x%d", drugName, DIN, dosage, frequency, quantity);
    }
}
