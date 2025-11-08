package domain.security;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User {

    @Id
    private String username;
    private String password;
    private String role; // PHARMACIST, ASSISTANT, ADMIN...

    protected User() {} // requis par JPA

    public User(String username, String password, String role) {
        this.username = Objects.requireNonNull(username);
        this.password = Objects.requireNonNull(password);
        this.role = Objects.requireNonNull(role);
    }

    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public String getRole() { return role; }

    public void setPassword(String password) { this.password = password; }
    public void setRole(String role) { this.role = role; }
}
