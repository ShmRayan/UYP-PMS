package domain.prescription;

import java.time.LocalDate;
import java.util.List;
import domain.patient.Patient;
import org.springframework.stereotype.Component;

@Component
public class PrescriptionFactory {


    private int counter = 1;

    public PrescriptionFactory(PrescriptionRepository repository) {
        String lastId = repository.findLastId();
        if (lastId != null && lastId.startsWith("PRESC-")) {
            try {
                counter = Integer.parseInt(lastId.substring(6)) + 1;
            } catch (NumberFormatException ignored) {}
        }
    }

    private String nextId() {
        return String.format("PRESC-%03d", counter++);
    }

    public Prescription createExternalPrescriber(
            Patient patient,
            List<PrescriptionItem> items,
            String prescriberId
    ) {
        return new Prescription(
                new PrescriptionId(nextId()),
                LocalDate.now(),
                items,
                prescriberId,
                patient
        );
    }
}

