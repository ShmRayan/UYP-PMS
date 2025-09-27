Feature: Prepare Prescription Fill

  Scenario: Agent prepares a prescription for filling
    Given a valid prescription exists
    When the agent prepares the prescription fill
    Then the system marks it as ready for pharmacist validation
