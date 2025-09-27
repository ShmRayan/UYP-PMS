package application.usecases.command;

public class RegisterPatientCommand {
    public String name;
    public String healthNumber;
    public String address;

    public RegisterPatientCommand(String name, String healthNumber, String address) {
        this.name = name;
        this.healthNumber = healthNumber;
        this.address = address;
    }
}
