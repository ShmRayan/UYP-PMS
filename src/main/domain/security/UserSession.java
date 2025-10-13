package domain.security;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

import domain.agent.PharmacyAgent;

public class UserSession {
    private final String sessionId;
    private final LocalDateTime loginTime;
    private final LocalDateTime expiryTime;
    private final PharmacyAgent user;
    private boolean active;

    public UserSession(PharmacyAgent user, int sessionDurationMinutes) {
        this.sessionId = UUID.randomUUID().toString();
        this.loginTime = LocalDateTime.now();
        this.expiryTime = loginTime.plusMinutes(sessionDurationMinutes);
        this.user = Objects.requireNonNull(user);
        this.active = true;
    }

    public String getSessionId() { return sessionId; }
    public LocalDateTime getLoginTime() { return loginTime; }
    public LocalDateTime getExpiryTime() { return expiryTime; }
    public PharmacyAgent getUser() { return user; }

    public boolean isActive() {
        return active && LocalDateTime.now().isBefore(expiryTime);
    }

    public void endSession() {
        this.active = false;
    }
}
