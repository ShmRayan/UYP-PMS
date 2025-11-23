package application.usecases.command;

public class CreatePrescriptionCommand {

    public String patientId;
    public String prescriberId;
    public String din;
    public String drugName;
    public String strength;
    public String adminMethod;
    public String frequency;
    public String instructions;
    public int quantity;
    public String refillType;
    public int refillCount;

    public CreatePrescriptionCommand(
            String patientId,
            String prescriberId,
            String drugName,
            String din,
            String strength,
            String adminMethod,
            String frequency,
            String instructions,
            int quantity,
            String refillType,
            int refillCount
    ) {
        this.patientId = patientId;
        this.prescriberId = prescriberId;
        this.drugName = drugName;
        this.din = din;
        this.strength = strength;
        this.adminMethod = adminMethod;
        this.frequency = frequency;
        this.instructions = instructions;
        this.quantity = quantity;
        this.refillType = refillType;
        this.refillCount = refillCount;
    }
}
