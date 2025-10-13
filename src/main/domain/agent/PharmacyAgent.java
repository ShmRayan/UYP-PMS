package domain.agent;

public class PharmacyAgent {
    private final AgentId id;
    private String name;
    private AgentRole role;
    private AgentStatus status;

    public PharmacyAgent(AgentId id, String name, AgentRole role) {
        this.id = id;
        this.name = name;
        this.role = role;
        this.status = AgentStatus.ACTIVE;
    }

    public AgentId getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public AgentRole getRole() {
        return role;
    }

    public AgentStatus getStatus() {
        return status;
    }

    public void activate() {
        this.status = AgentStatus.ACTIVE;
    }

    public void deactivate() {
        this.status = AgentStatus.INACTIVE;
    }

    public void updateInfo(String newName, AgentRole newRole) {
        this.name = newName;
        this.role = newRole;
    }

    @Override
    public String toString() {
        return String.format("Agent[id=%s, name=%s, role=%s, status=%s]",
                id, name, role, status);
    }
}
