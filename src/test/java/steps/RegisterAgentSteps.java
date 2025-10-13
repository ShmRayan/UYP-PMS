package steps;

import domain.agent.AgentFactory;
import domain.agent.AgentRole;
import domain.agent.PharmacyAgent;
import infrastructure.repository.inmemory.InMemoryAgentRepository;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class RegisterAgentSteps {

    private final InMemoryAgentRepository agentRepo = new InMemoryAgentRepository();
    private PharmacyAgent newAgent;

    @Given("an administrator is authenticated")
    public void administrator_authenticated() {
        System.out.println("Administrator authenticated");
    }

    @When("they provide valid agent information")
    public void provide_agent_info() {
        AgentFactory factory = new AgentFactory();
        newAgent = factory.createAgent("Alice Martin", AgentRole.ASSISTANT);
        agentRepo.save(newAgent);
    }

    @Then("the system adds the new agent with credentials")
    public void system_adds_new_agent() {
        PharmacyAgent found = agentRepo.findById(newAgent.getId());
        assert found != null : "Agent not saved!";
        System.out.println("Agent added successfully: " + found);
    }
}
