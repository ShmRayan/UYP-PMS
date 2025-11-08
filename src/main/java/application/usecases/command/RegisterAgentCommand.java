package application.usecases.command;

public class RegisterAgentCommand {
    public String name;
    public String role; // Pharmacist or Assistant
    public String email;

    public RegisterAgentCommand(String name, String role, String email) {
        this.name = name;
        this.role = role;
        this.email = email;
    }
}
