package steps;

import io.cucumber.java.en.*;

public class CreatePrescriptionSteps {
    
    @When("the agent enters prescription details with valid DIN")
    public void agent_enters_prescription_details() {
        System.out.println("Agent entered prescription details with valid DIN");
    }

    @Then("the system records the prescription with a unique ID")
    public void system_records_prescription() {
        System.out.println("System recorded prescription with unique ID");
    }
}
