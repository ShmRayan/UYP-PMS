/*
package steps;

import java.time.LocalDate;
import java.util.List;

import domain.agent.AgentFactory;
import domain.agent.PharmacyAgent;
import domain.patient.Address;
import domain.patient.HealthId;
import domain.patient.InsuranceInfo;
import domain.patient.Patient;
import domain.prescription.Prescription;
import domain.prescription.PrescriptionFactory;
import domain.prescription.PrescriptionItem;
import infrastructure.repository.inmemory.InMemoryAgentRepository;
import infrastructure.repository.inmemory.InMemoryPatientRepository;
import infrastructure.repository.inmemory.InMemoryPrescriptionRepository;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class CreatePrescriptionSteps {

    private final InMemoryPatientRepository patientRepo = new InMemoryPatientRepository();
    private final InMemoryAgentRepository agentRepo = new InMemoryAgentRepository();
    private final InMemoryPrescriptionRepository prescriptionRepo = new InMemoryPrescriptionRepository();

    private Patient patient;
    private PharmacyAgent agent;
    private Prescription prescription;

    @Given("a patient is registered and the prescriber is valid")
    public void patient_and_prescriber_valid() {
        HealthId healthId = new HealthId("H123456");
        Address address = new Address("123 Main St", "Ottawa", "ON", "K1A0B1");
        InsuranceInfo insurance = new InsuranceInfo("POL123", "SunLife", LocalDate.of(2026, 5, 1));
        patient = new Patient(healthId, "John Smith", LocalDate.of(1990, 3, 15), address, insurance, List.of());
        patientRepo.save(patient);

        AgentFactory factory = new AgentFactory();
        //agent = factory.createAgent("Sarah Lee", AgentRole.ASSISTANT);
        agentRepo.save(agent);
    }

    @When("the agent enters prescription details with valid DIN")
    public void agent_enters_prescription_details() {
        PrescriptionItem item = new PrescriptionItem("Amoxicillin", "12345678", "500mg", "3/day",30);
        PrescriptionFactory factory = new PrescriptionFactory();
        prescription = factory.create(patient, List.of(item), agent);
        prescriptionRepo.save(prescription);
    }

    @Then("the system records the prescription with a unique ID")
    public void system_records_prescription() {
        Prescription found = prescriptionRepo.findById(prescription.getId());
        assert found != null : "Prescription not saved!";
        System.out.println("Prescription saved successfully: " + found);
    }
}
*/