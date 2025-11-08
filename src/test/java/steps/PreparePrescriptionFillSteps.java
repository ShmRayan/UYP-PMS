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

public class PreparePrescriptionFillSteps {

    private final InMemoryPrescriptionRepository prescriptionRepo = new InMemoryPrescriptionRepository();
    private Prescription prescription;
    private PharmacyAgent agent;

    @Given("a valid prescription exists")
    public void valid_prescription_exists() {
        AgentFactory factory = new AgentFactory();
        agent = factory.createAgent("Sarah Lee", AgentRole.ASSISTANT);
        Patient patient = new Patient(new HealthId("H001"), "John Doe", LocalDate.of(1990, 1, 1),
                new Address("1 King St", "Ottawa", "ON", "K1A0A1"),
                new InsuranceInfo("POL123", "SunLife", LocalDate.of(2026, 1, 1)), List.of());
        PrescriptionItem item = new PrescriptionItem("Aspirin", "12345", "500mg", "1/day",15);
        prescription = new PrescriptionFactory().create(patient, List.of(item), agent);
        prescriptionRepo.save(prescription);
    }

    @When("the agent prepares the prescription fill")
    public void agent_prepares_prescription_fill() {
        prescription.markPrepared(agent);
        prescriptionRepo.save(prescription);
    }

    @Then("the system marks it as ready for pharmacist validation")
    public void system_marks_ready_for_validation() {
        Prescription updated = prescriptionRepo.findById(prescription.getId());
        assert updated.getStatus() == PrescriptionStatus.PREPARED : "Prescription not prepared!";
        System.out.println("Prescription marked as PREPARED: " + updated);
    }
}
