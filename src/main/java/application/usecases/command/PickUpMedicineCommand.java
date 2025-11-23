package application.usecases.command;

public class PickUpMedicineCommand {
    public String prescriptionId;
    public String pickupNotes;

    public PickUpMedicineCommand(String prescriptionId, String pickupNotes) {
        this.prescriptionId = prescriptionId;
        this.pickupNotes = pickupNotes;
    }
}
