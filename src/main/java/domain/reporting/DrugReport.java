package domain.reporting;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "drug_reports")
public class DrugReport {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID) 
    private String id;

    private String drugDIN;
    private String period; 
    private int totalPrescriptions;
    private int totalQuantity;

    protected DrugReport() {}

    public DrugReport(String drugDIN, String period, int totalPrescriptions, int totalQuantity) {
        this.drugDIN = Objects.requireNonNull(drugDIN);
        this.period = Objects.requireNonNull(period);
        this.totalPrescriptions = totalPrescriptions;
        this.totalQuantity = totalQuantity;
    }

    public String getId() { return id; }
    public String getDrugDIN() { return drugDIN; }
    public String getPeriod() { return period; }
    public int getTotalPrescriptions() { return totalPrescriptions; }
    public int getTotalQuantity() { return totalQuantity; }


    public String summarize() {
        return String.format("Drug Report [%s]: %d prescriptions, %d units dispensed (%s)",
                drugDIN, totalPrescriptions, totalQuantity, period);
    }
}
