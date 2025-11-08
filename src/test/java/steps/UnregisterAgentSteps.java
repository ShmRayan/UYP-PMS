package steps;

import domain.agent.AgentFactory;
import domain.agent.AgentId;
import domain.agent.AgentRole;
import domain.agent.PharmacyAgent;
import infrastructure.repository.inmemory.InMemoryAgentRepository;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class UnregisterAgentSteps {

    private final InMemoryAgentRepository agentRepo = new InMemoryAgentRepository();
    private PharmacyAgent agent;

    @Given("an administrator is authenticated and an agent exists")
    public void administrator_and_agent_exists() {
        AgentFactory factory = new AgentFactory();
        agent = factory.createAgent("Laura King", AgentRole.PHARMACIST);
        agentRepo.save(agent);
    }

    @When("the administrator confirms unregistering")
    public void administrator_confirms_unregister() {
        agent.deactivate();
        agentRepo.save(agent);
    }

    @Then("the system disables the agent account")
    public void system_disables_agent() {     
        AgentId id = new AgentId(agent.getId());
        PharmacyAgent disabled = agentRepo.findById(id);

        assert disabled != null && !disabled.isActive() : "Agent not deactivated!";
        System.out.println("✅ Agent account deactivated: " + disabled.getName());
    }
}
