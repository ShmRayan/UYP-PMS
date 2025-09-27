package application.usecases.command;

public class PickUpMedicineCommand {
    public String prescriptionId;
    public String patientId;

    public PickUpMedicineCommand(String prescriptionId, String patientId) {
        this.prescriptionId = prescriptionId;
        this.patientId = patientId;
    }
}
