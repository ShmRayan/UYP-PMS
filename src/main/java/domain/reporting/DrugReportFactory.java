package domain.reporting;

import java.util.Objects;
import org.springframework.stereotype.Component;

@Component 
public class DrugReportFactory {

    public DrugReport create(String drugDIN, String period, int totalPrescriptions, int totalQuantity) {
        Objects.requireNonNull(drugDIN, "drugDIN cannot be null");
        Objects.requireNonNull(period, "period cannot be null");

        
        DrugReport report = new DrugReport(
                drugDIN,
                period,
                totalPrescriptions,
                totalQuantity
        );

        return report;
    }
}
