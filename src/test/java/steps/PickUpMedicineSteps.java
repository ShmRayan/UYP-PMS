package steps;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class PickUpMedicineSteps {

    @When("the patient presents valid identification")
    public void patient_presents_id() {
        System.out.println("Patient presented valid ID");
    }

    @Then("the system records the medicine as dispensed")
    public void system_records_dispensed() {
        System.out.println("System recorded medicine as dispensed");
    }
}
