package steps;

import io.cucumber.java.en.*;

public class UnregisterAgentSteps {

    @Given("an administrator is authenticated and an agent exists")
    public void administrator_and_agent_exists() {
        System.out.println("Administrator authenticated and agent exists");
    }

    @When("the administrator confirms unregistering")
    public void administrator_confirms_unregister() {
        System.out.println("Administrator confirmed unregistering");
    }

    @Then("the system disables the agent account")
    public void system_disables_agent() {
        System.out.println("System disabled the agent account");
    }
}
