package domain.prescription;

import java.util.Objects;

public class PrescriptionItem {

    private final String drugName;
    private final String din;
    private final String strength;
    private final String administrationMethod;
    private final String frequency;
    private final String instructions;
    private final int quantity;
    private final String refillType;
    private final int refillCount;

    public PrescriptionItem(
            String drugName,
            String din,
            String strength,
            String administrationMethod,
            String frequency,
            String instructions,
            int quantity,
            String refillType,
            int refillCount
    ) {
        this.drugName = Objects.requireNonNull(drugName);
        this.din = Objects.requireNonNull(din);
        this.strength = Objects.requireNonNull(strength);
        this.administrationMethod = Objects.requireNonNull(administrationMethod);
        this.frequency = Objects.requireNonNull(frequency);
        this.instructions = instructions;
        this.quantity = quantity;
        this.refillType = refillType;
        this.refillCount = refillCount;
    }

    public String getDrugName() { return drugName; }
    public String getDin() { return din; }
    public String getStrength() { return strength; }
    public String getAdministrationMethod() { return administrationMethod; }
    public String getFrequency() { return frequency; }
    public String getInstructions() { return instructions; }
    public int getQuantity() { return quantity; }
    public String getRefillType() { return refillType; }
    public int getRefillCount() { return refillCount; }
}
