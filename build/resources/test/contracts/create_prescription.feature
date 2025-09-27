Feature: Create Prescription

  Scenario: Pharmacist or assistant creates a prescription
    Given a patient is registered and the prescriber is valid
    When the agent enters prescription details with valid DIN
    Then the system records the prescription with a unique ID
