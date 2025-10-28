Feature: Update Patient
  As a pharmacist
  I want to update patient information
  So that patient records remain current and accurate

  Background:
    Given I am authenticated as a pharmacist
    And a patient with health ID "1234567890" exists in the system

  Scenario: Successfully update patient address
    When I update the patient's address to "789 New Street"
    Then the update should be successful
    And the patient's address should be "789 New Street"

  Scenario: Successfully update patient allergies
    When I update the patient's allergies to include:
      | Penicillin    |
      | Sulfa drugs   |
    Then the update should be successful
    And the patient should have 2 allergies recorded

  Scenario: Successfully update patient current medications
    When I update the patient's current medications to include:
      | Aspirin 81mg  |
      | Lisinopril    |
    Then the update should be successful

  Scenario: Successfully update multiple patient fields
    When I update the patient with the following information:
      | address           | 999 Updated Rd  |
      | languagePreference| French          |
      | insuranceNumber   | INS999999       |
    Then the update should be successful

  Scenario: Fail to update non-existent patient
    Given a patient with health ID "0000000000" does not exist
    When I attempt to update patient with health ID "0000000000"
    Then the update should fail
    And the system should return an error message "Patient not found"

  Scenario: Update patient with partial information
    When I update only the patient's email preference to "French"
    Then the update should be successful
    And other patient information should remain unchanged
