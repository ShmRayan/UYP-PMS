package domain.agent;

import java.util.UUID;

public class AgentId {
    private final String value;

    public AgentId() {
        this.value = UUID.randomUUID().toString();
    }

    public AgentId(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof AgentId)) return false;
        AgentId other = (AgentId) obj;
        return value.equals(other.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    @Override
    public String toString() {
        return value;
    }
}
