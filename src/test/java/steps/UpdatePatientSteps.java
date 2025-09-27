package steps;

import io.cucumber.java.en.*;

public class UpdatePatientSteps {

    @Given("a pharmacist selects an existing patient")
    public void pharmacist_selects_existing_patient() {
        System.out.println("Pharmacist selected an existing patient");
    }

    @When("they update patient details with valid data")
    public void update_patient_details() {
        System.out.println("Pharmacist updated patient details");
    }

    @Then("the system updates the patient record")
    public void system_updates_patient_record() {
        System.out.println("System updated the patient record");
    }
}
