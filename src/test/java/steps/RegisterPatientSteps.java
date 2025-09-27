package steps;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class RegisterPatientSteps {

    @When("they provide valid patient information and consent")
    public void provide_patient_info_and_consent() {
        System.out.println("Pharmacist provided valid patient info and consent");
    }

    @Then("the system creates a patient record with a unique identifier")
    public void system_creates_patient_record() {
        System.out.println("System created patient record with unique ID");
    }
}
