package steps;

import domain.agent.AgentFactory;
import domain.agent.AgentId;
import domain.agent.AgentRole;
import domain.agent.PharmacyAgent;
import infrastructure.repository.inmemory.InMemoryAgentRepository;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class UpdateAgentSteps {

    private final InMemoryAgentRepository agentRepo = new InMemoryAgentRepository();
    private PharmacyAgent existingAgent;

    @Given("an administrator selects an existing agent")
    public void administrator_selects_agent() {
        AgentFactory factory = new AgentFactory();
        existingAgent = factory.createAgent("Mark Johnson", AgentRole.ASSISTANT);
        agentRepo.save(existingAgent);
    }

    @When("they modify the agent information")
    public void modify_agent_info() {
        existingAgent = new PharmacyAgent(
                new AgentId(existingAgent.getId()), 
                "Mark Johnson Updated",
                existingAgent.getEmail(),
                existingAgent.getPassword(),
                AgentRole.PHARMACIST
        );
        agentRepo.save(existingAgent);
    }

    @Then("the system updates the agent record")
    public void system_updates_agent_record() {
        AgentId id = new AgentId(existingAgent.getId());
        PharmacyAgent updated = agentRepo.findById(id);

        assert updated != null : "Agent not found!";
        System.out.println(" Agent updated successfully: " + updated.getName() +
                " (" + updated.getRole() + ")");
    }
}
