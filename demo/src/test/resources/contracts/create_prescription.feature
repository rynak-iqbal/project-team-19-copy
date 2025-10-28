Feature: Create Prescription
  As a pharmacy agent
  I want to create a new prescription
  So that medication can be prepared for a patient

  Background:
    Given I am authenticated as an agent
    And a patient with health ID "1234567890" exists in the system

  Scenario: Successfully create a non-refillable prescription
    Given the prescriber with license "MD12345" is valid
    And the drug with DIN "02241234" exists in the database
    When I create a prescription with the following details:
      | patientHealthId       | 1234567890              |
      | prescriberLicenseNumber| MD12345                |
      | drugDIN               | 02241234                |
      | drugName              | Amoxicillin             |
      | medicationStrength    | 500mg                   |
      | amount                | 30 capsules             |
      | administrationMethod  | oral                    |
      | frequency             | 3 times daily           |
      | considerations        | with food               |
      | refillType            | NON_REFILLABLE          |
      | prescriptionDate      | 2025-10-06              |
    Then the prescription should be successfully created
    And the system should return a prescription ID
    And the prescription status should be "CREATED"

  Scenario: Successfully create a refillable prescription
    Given the prescriber with license "MD12345" is valid
    And the drug with DIN "02241234" exists in the database
    When I create a refillable prescription with 3 refills
    Then the prescription should be successfully created
    And the prescription should have 3 refills remaining

  Scenario: Successfully create a prescription requiring authorization for refills
    Given the prescriber with license "MD12345" is valid
    And the drug with DIN "02241234" exists in the database
    When I create a prescription requiring authorization with 2 refills
    Then the prescription should be successfully created
    And the prescription should require authorization for refills

  Scenario: Fail to create prescription for non-existent patient
    Given a patient with health ID "0000000000" does not exist
    When I attempt to create a prescription for patient "0000000000"
    Then the prescription creation should fail
    And the system should return an error message "Patient not found"

  Scenario: Fail to create prescription with invalid prescriber
    Given the prescriber with license "INVALID123" is not found in the registry
    When I attempt to create a prescription with prescriber "INVALID123"
    Then the prescription creation should fail
    And the system should return an error message "Invalid prescriber license"

  Scenario: Fail to create prescription with invalid drug DIN
    Given the drug with DIN "99999999" does not exist in the database
    When I attempt to create a prescription with drug DIN "99999999"
    Then the prescription creation should fail
    And the system should return an error message "Invalid drug DIN"

  Scenario: Fail to create prescription with missing required information
    When I attempt to create a prescription with incomplete information
    Then the prescription creation should fail
    And the system should return an error message indicating missing fields
