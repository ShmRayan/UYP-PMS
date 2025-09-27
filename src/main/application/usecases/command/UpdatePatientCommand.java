package application.usecases.command;

public class UpdatePatientCommand {
    public String patientId;
    public String name;
    public String address;

    public UpdatePatientCommand(String patientId, String name, String address) {
        this.patientId = patientId;
        this.name = name;
        this.address = address;
    }
}
