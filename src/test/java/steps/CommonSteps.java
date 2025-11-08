package steps;

import io.cucumber.java.en.Given;

public class CommonSteps {

    @Given("a pharmacist is authenticated")
    public void pharmacist_authenticated() {
        System.out.println("Pharmacist authenticated");
    }

    @Given("an administrator is authenticated")
    public void administrator_authenticated() {
        System.out.println("Administrator authenticated");
    }

    @Given("an agent is authenticated")
    public void agent_authenticated() {
        System.out.println("Agent authenticated");
    }

    @Given("a patient is registered and the prescriber is valid")
    public void patient_and_prescriber_valid() {
        System.out.println("Patient registered and prescriber validated");
    }

    @Given("a valid prescription exists")
    public void valid_prescription_exists() {
        System.out.println("Valid prescription exists");
    }

    @Given("the prescription is ready for pickup")
    public void prescription_ready_for_pickup() {
        System.out.println("Prescription ready for pickup");
    }
}
