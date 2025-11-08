package application.usecases.command;

public class CreatePrescriptionCommand {
    public String patientId;
    public String prescriberId;
    public String din; 
    public String instructions;
    public int quantity;

    public CreatePrescriptionCommand(String patientId, String prescriberId, String din, String instructions, int quantity) {
        this.patientId = patientId;
        this.prescriberId = prescriberId;
        this.din = din;
        this.instructions = instructions;
        this.quantity = quantity;
    }
}
