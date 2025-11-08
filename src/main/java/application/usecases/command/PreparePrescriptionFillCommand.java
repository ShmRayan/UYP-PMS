package application.usecases.command;

public class PreparePrescriptionFillCommand {
    public String prescriptionId;
    public int quantity;
    public String lotNumber;
    public String expiryDate;

    public PreparePrescriptionFillCommand(String prescriptionId, int quantity, String lotNumber, String expiryDate) {
        this.prescriptionId = prescriptionId;
        this.quantity = quantity;
        this.lotNumber = lotNumber;
        this.expiryDate = expiryDate;
    }
}
