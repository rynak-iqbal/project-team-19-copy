Feature: Register Patient
  As a pharmacist
  I want to register a new patient
  So that I can fill prescriptions for them

  Background:
    Given I am authenticated as a pharmacist

  Scenario: Successfully register an adult patient with complete information
    Given the patient does not exist in the system
    And the patient has signed the consent form
    When I register a patient with the following information:
      | healthId          | 1234567890      |
      | firstName         | John            |
      | lastName          | Doe             |
      | address           | 123 Main St     |
      | gender            | Male            |
      | dateOfBirth       | 1985-05-15      |
      | languagePreference| English         |
      | allergies         | Penicillin      |
      | currentMedications| Aspirin         |
      | insuranceNumber   | INS123456       |
    Then the patient should be successfully registered
    And the system should return the health ID "1234567890"

  Scenario: Successfully register a minor patient with guardian information
    Given the patient does not exist in the system
    And the guardian has signed the consent form
    When I register a minor patient with the following information:
      | healthId          | 9876543210      |
      | firstName         | Jane            |
      | lastName          | Smith           |
      | address           | 456 Oak Ave     |
      | gender            | Female          |
      | dateOfBirth       | 2015-08-20      |
      | languagePreference| English         |
      | guardianName      | Mary Smith      |
    Then the patient should be successfully registered

  Scenario: Fail to register patient with duplicate health ID
    Given a patient with health ID "1234567890" already exists
    When I attempt to register a patient with health ID "1234567890"
    Then the registration should fail
    And the system should return an error message "Patient with this health ID already exists"

  Scenario: Fail to register patient without consent
    Given the patient does not exist in the system
    And the consent form has not been signed
    When I attempt to register a patient
    Then the registration should fail
    And the system should return an error message "Consent form must be signed"

  Scenario: Fail to register patient with missing required information
    Given the patient does not exist in the system
    When I attempt to register a patient with incomplete information
    Then the registration should fail
    And the system should return an error message indicating missing fields
