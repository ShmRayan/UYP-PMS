package steps;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class PreparePrescriptionFillSteps {

    @When("the agent prepares the prescription fill")
    public void agent_prepares_prescription_fill() {
        System.out.println("Agent prepared prescription fill");
    }

    @Then("the system marks it as ready for pharmacist validation")
    public void system_marks_ready_for_validation() {
        System.out.println("System marked prescription as ready for validation");
    }
}
