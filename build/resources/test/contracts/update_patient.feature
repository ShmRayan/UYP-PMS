Feature: Update Patient

  Scenario: Pharmacist updates patient information
    Given a pharmacist selects an existing patient
    When they update patient details with valid data
    Then the system updates the patient record
