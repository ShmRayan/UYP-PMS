package domain.agent;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;

@Entity
public class PharmacyAgent {

    @Id
    private String id;
    private String name;
    private String email;
    private String password;
    

    @Enumerated(EnumType.STRING)
    private AgentRole role;

    private boolean active = true;

    public PharmacyAgent() {}

    public PharmacyAgent(AgentId id, String name, String email, String password, AgentRole role) {
        this.id = id.getValue();
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
    }
    public PharmacyAgent(AgentId id, String name, AgentRole role) {
        this.id = id.getValue();
        this.name = name;
        this.email = name.toLowerCase() + "@uypms.com";
        this.password = "default123";
        this.role = role;
        this.active = true;
    }

    public void updateInfo(String newName, AgentRole newRole) {
        if (newName != null && !newName.isBlank()) {
            this.name = newName;
            this.email = newName.toLowerCase() + "@uypms.com";
        }
        if (newRole != null) {
            this.role = newRole;
        }
    }
    public void updateEmail(String newEmail) {
        this.email = newEmail;
    }

    public void updatePassword(String newPassword) {
        this.password = newPassword;
    }


    // Getters
    public String getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }
    public AgentRole getRole() { return role; }
    public boolean isActive() { return active; }

    public void deactivate() { this.active = false; }
}
