package domain.prescription;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Component;

import domain.agent.PharmacyAgent;
import domain.patient.Patient;

@Component
public class PrescriptionFactory {

    public Prescription create(Patient patient, List<PrescriptionItem> items, PharmacyAgent agent) {
        Objects.requireNonNull(patient);
        Objects.requireNonNull(items);
        Objects.requireNonNull(agent);

        return new Prescription(
                new PrescriptionId(),
                LocalDate.now(),
                items,
                agent,
                patient
        );
    }
}
