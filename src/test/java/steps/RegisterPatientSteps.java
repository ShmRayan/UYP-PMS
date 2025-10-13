package steps;

import java.time.LocalDate;
import java.util.List;

import domain.patient.Address;
import domain.patient.HealthId;
import domain.patient.InsuranceInfo;
import domain.patient.Patient;
import infrastructure.repository.inmemory.InMemoryPatientRepository;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class RegisterPatientSteps {

    private final InMemoryPatientRepository patientRepo = new InMemoryPatientRepository();
    private Patient patient;

    @Given("a pharmacist is authenticated")
    public void pharmacist_authenticated() {
        System.out.println("Pharmacist authenticated");
    }

    @When("they provide valid patient information and consent")
    public void provide_patient_info_and_consent() {
        HealthId healthId = new HealthId("H987654");
        Address address = new Address("456 Elm St", "Ottawa", "ON", "K2A1C1");
        InsuranceInfo insurance = new InsuranceInfo("POL456", "Manulife", LocalDate.of(2026, 6, 1));
        patient = new Patient(healthId, "Jane Doe", LocalDate.of(1988, 5, 20), address, insurance, List.of());
        patientRepo.save(patient);
    }

    @Then("the system creates a patient record with a unique identifier")
    public void system_creates_patient_record() {
        Patient found = patientRepo.findByHealthId(patient.getHealthId());
        assert found != null : " Patient not saved!";
        System.out.println("Patient record created successfully: " + found);
    }
}
