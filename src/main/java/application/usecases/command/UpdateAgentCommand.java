package application.usecases.command;

public class UpdateAgentCommand {

    public String agentId;
    public String name;
    public String email;
    public String newPassword;
    public String role;

    public UpdateAgentCommand(
            String agentId,
            String name,
            String email,
            String newPassword,
            String role
    ) {
        this.agentId = agentId;
        this.name = name;
        this.email = email;
        this.newPassword = newPassword;
        this.role = role;
    }
}
