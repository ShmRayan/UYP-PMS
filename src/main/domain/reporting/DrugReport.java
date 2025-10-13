package domain.reporting;

import java.util.Objects;

public class DrugReport {
    private final ReportId id;
    private final String drugDIN;
    private final ReportPeriod period;
    private final int totalPrescriptions;
    private final int totalQuantity;

    public DrugReport(ReportId id, String drugDIN, ReportPeriod period, int totalPrescriptions, int totalQuantity) {
        this.id = Objects.requireNonNull(id);
        this.drugDIN = Objects.requireNonNull(drugDIN);
        this.period = Objects.requireNonNull(period);
        this.totalPrescriptions = totalPrescriptions;
        this.totalQuantity = totalQuantity;
    }

    public ReportId getId() { return id; }
    public String getDrugDIN() { return drugDIN; }
    public ReportPeriod getPeriod() { return period; }
    public int getTotalPrescriptions() { return totalPrescriptions; }
    public int getTotalQuantity() { return totalQuantity; }

    public String summarize() {
        return String.format("Drug Report [%s]: %d prescriptions, %d units dispensed (%s)",
                drugDIN, totalPrescriptions, totalQuantity, period);
    }
}
