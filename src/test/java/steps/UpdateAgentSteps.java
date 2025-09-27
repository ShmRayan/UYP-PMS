package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class UpdateAgentSteps {

    @Given("an administrator selects an existing agent")
    public void administrator_selects_agent() {
        System.out.println("Administrator selected an existing agent");
    }

    @When("they modify the agent information")
    public void modify_agent_info() {
        System.out.println("Administrator modified agent information");
    }

    @Then("the system updates the agent record")
    public void system_updates_agent_record() {
        System.out.println("System updated agent record");
    }
}
