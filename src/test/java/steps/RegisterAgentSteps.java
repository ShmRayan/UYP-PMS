package steps;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class RegisterAgentSteps {

    @When("they provide valid agent information")
    public void provide_agent_info() {
        System.out.println("Administrator provided valid agent information");
    }

    @Then("the system adds the new agent with credentials")
    public void system_adds_new_agent() {
        System.out.println("System added new agent with credentials");
    }
}
