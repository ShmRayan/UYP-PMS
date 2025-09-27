package application.usecases.command;

public class UnregisterAgentCommand {
    public String agentId;

    public UnregisterAgentCommand(String agentId) {
        this.agentId = agentId;
    }
}
