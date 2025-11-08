package domain.reporting;

import java.util.Objects;

public final class ReportId {
    private final String value;

    public ReportId(String value) {
        this.value = Objects.requireNonNull(value);
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ReportId)) return false;
        ReportId reportId = (ReportId) o;
        return value.equals(reportId.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return value;
    }
}
