package application.usecases.command;

import java.time.LocalDate;

public class PreparePrescriptionFillCommand {
    public String prescriptionId;
    public int quantity;
    public String lotNumber;
    public LocalDate expiryDate;

    public PreparePrescriptionFillCommand(
            String prescriptionId,
            int quantity,
            String lotNumber,
            LocalDate expiryDate
    ) {
        this.prescriptionId = prescriptionId;
        this.quantity = quantity;
        this.lotNumber = lotNumber;
        this.expiryDate = expiryDate;
    }
}
