package domain.prescription;

import java.util.Objects;
import java.util.UUID;

public final class PrescriptionId {
    private final String value;

    public PrescriptionId() {
        this.value = UUID.randomUUID().toString();
    }

    public PrescriptionId(String value) {
        this.value = Objects.requireNonNull(value);
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PrescriptionId)) return false;
        PrescriptionId other = (PrescriptionId) o;
        return value.equals(other.value);
    }

    @Override
    public int hashCode() { return Objects.hash(value); }

    @Override
    public String toString() { return value; }
}
