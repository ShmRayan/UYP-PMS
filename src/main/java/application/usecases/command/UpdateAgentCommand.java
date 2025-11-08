package application.usecases.command;

public class UpdateAgentCommand {
    public String agentId;
    public String name;
    public String role;

    public UpdateAgentCommand(String agentId, String name, String role) {
        this.agentId = agentId;
        this.name = name;
        this.role = role;
    }
}
