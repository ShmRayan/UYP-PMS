package domain.patient;

import java.util.Objects;

public final class HealthId {
    private final String value;

    public HealthId(String value) {
        if (value == null || value.isBlank())
            throw new IllegalArgumentException("HealthId cannot be null/blank");
        this.value = value;
    }

    public String getValue() { return value; }

    @Override public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof HealthId)) return false;
        HealthId healthId = (HealthId) o;
        return value.equals(healthId.value);
    }
    @Override public int hashCode() { return Objects.hash(value); }
    @Override public String toString() { return value; }
}
