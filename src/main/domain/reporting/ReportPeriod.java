package domain.reporting;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

public final class ReportPeriod {
    private final LocalDate startDate;
    private final LocalDate endDate;

    public ReportPeriod(LocalDate startDate, LocalDate endDate) {
        if (endDate.isBefore(startDate)) {
            throw new IllegalArgumentException("End date must be after start date");
        }
        this.startDate = Objects.requireNonNull(startDate);
        this.endDate = Objects.requireNonNull(endDate);
    }

    public LocalDate getStartDate() { return startDate; }
    public LocalDate getEndDate() { return endDate; }

    public int durationDays() {
        return (int) ChronoUnit.DAYS.between(startDate, endDate);
    }

    @Override
    public String toString() {
        return startDate + " to " + endDate;
    }
}
