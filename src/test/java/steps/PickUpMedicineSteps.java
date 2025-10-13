package steps;

import java.time.LocalDate;
import java.util.List;

import domain.agent.AgentFactory;
import domain.agent.AgentRole;
import domain.agent.PharmacyAgent;
import domain.patient.Address;
import domain.patient.HealthId;
import domain.patient.InsuranceInfo;
import domain.patient.Patient;
import domain.prescription.Prescription;
import domain.prescription.PrescriptionFactory;
import domain.prescription.PrescriptionItem;
import domain.prescription.PrescriptionStatus;
import infrastructure.repository.inmemory.InMemoryPrescriptionRepository;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class PickUpMedicineSteps {

    private final InMemoryPrescriptionRepository prescriptionRepo = new InMemoryPrescriptionRepository();
    private Patient patient;
    private Prescription prescription;

    @Given("the prescription is ready for pickup")
    public void prescription_ready_for_pickup() {
        Patient p = new Patient(new HealthId("H111"), "Lisa Grey", LocalDate.of(1985, 6, 5),
                new Address("22 Rideau St", "Ottawa", "ON", "K1A0A9"),
                new InsuranceInfo("POL777", "CanadaLife", LocalDate.of(2026, 7, 1)), List.of());
        patient = p;
        AgentFactory factory = new AgentFactory();
        PharmacyAgent agent = factory.createAgent("Bob Smith", AgentRole.PHARMACIST);

        PrescriptionItem item = new PrescriptionItem("Tylenol", "22222", "250mg", "2/day");
        prescription = new PrescriptionFactory().create(p, List.of(item), agent);
        prescription.markPrepared(agent);
        prescription.verify(agent);
        prescriptionRepo.save(prescription);
    }

    @When("the patient presents valid identification")
    public void patient_presents_id() {
        System.out.println("🪪 Patient " + patient.getName() + " presented valid ID");
    }

    @Then("the system records the medicine as dispensed")
    public void system_records_dispensed() {
        prescription.markPickedUp(patient);
        prescriptionRepo.save(prescription);

        Prescription updated = prescriptionRepo.findById(prescription.getId());
        assert updated.getStatus() == PrescriptionStatus.PICKED_UP : "Prescription not marked as picked up!";
        System.out.println("Prescription marked as PICKED_UP for: " + updated.getPatient().getName());
    }
}
