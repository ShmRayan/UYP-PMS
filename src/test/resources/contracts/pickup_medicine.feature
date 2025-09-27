Feature: Pick-Up Medicine

  Scenario: Patient collects prepared medicine
    Given the prescription is ready for pickup
    When the patient presents valid identification
    Then the system records the medicine as dispensed
