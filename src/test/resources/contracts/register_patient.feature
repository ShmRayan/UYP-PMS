Feature: Register Patient

  Scenario: Pharmacist registers a new patient
    Given a pharmacist is authenticated
    When they provide valid patient information and consent
    Then the system creates a patient record with a unique identifier
