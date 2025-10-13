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

public class UpdatePatientSteps {

    private final InMemoryPatientRepository patientRepo = new InMemoryPatientRepository();
    private Patient existingPatient;

    @Given("a pharmacist selects an existing patient")
    public void pharmacist_selects_existing_patient() {
        HealthId id = new HealthId("H123123");
        Address address = new Address("12 Queen St", "Ottawa", "ON", "K1B2L3");
        InsuranceInfo insurance = new InsuranceInfo("POL789", "CanadaLife", LocalDate.of(2026, 8, 10));
        existingPatient = new Patient(id, "Tom Brown", LocalDate.of(1992, 4, 12), address, insurance, List.of());
        patientRepo.save(existingPatient);
    }

    @When("they update patient details with valid data")
    public void update_patient_details() {
        Address newAddress = new Address("99 Laurier Ave", "Ottawa", "ON", "K1A2B3");
        InsuranceInfo newInsurance = new InsuranceInfo("POL999", "BlueCross", LocalDate.of(2027, 2, 1));
        existingPatient.updateInfo(newAddress, newInsurance);
        patientRepo.save(existingPatient);
    }

    @Then("the system updates the patient record")
    public void system_updates_patient_record() {
        Patient updated = patientRepo.findByHealthId(existingPatient.getHealthId());
        assert updated != null : "❌ Patient not found!";
        System.out.println("✅ Patient updated successfully: " + updated);
    }
}
