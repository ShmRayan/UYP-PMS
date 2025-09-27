package application.usecases.command;

public class CreatePrescriptionCommand {
    public String patientId;
    public String prescriberId;
    public String din; 
    public String instructions;

    public CreatePrescriptionCommand(String patientId, String prescriberId, String din, String instructions) {
        this.patientId = patientId;
        this.prescriberId = prescriberId;
        this.din = din;
        this.instructions = instructions;
    }
}
