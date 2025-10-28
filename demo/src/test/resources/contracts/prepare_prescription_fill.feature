Feature: Prepare Prescription Fill
  As a pharmacy agent
  I want to prepare and verify prescriptions
  So that patients can safely receive their medication

  Background:
    Given I am authenticated as an agent
    And a prescription with ID "RX001" exists with status "CREATED"

  Scenario: Successfully prepare medication
    Given I have located the medication in stock
    When I prepare the medication with the following details:
      | prescriptionId | RX001      |
      | lotNumber      | LOT12345   |
      | expiryDate     | 2026-12-31 |
    Then the medication preparation should be successful
    And the prescription status should be "PREPARED"
    And the prescription should be added to the verification queue

  Scenario: Successfully verify prescription as pharmacist
    Given I am authenticated as a pharmacist
    And the prescription with ID "RX001" has status "PREPARED"
    When I verify the prescription with clinical check notes "No interactions found"
    Then the verification should be successful
    And the prescription status should be "VERIFIED"

  Scenario: Successfully finalize preparation
    Given the prescription with ID "RX001" has status "VERIFIED"
    When I finalize the preparation
    Then the finalization should be successful
    And drug information documents should be generated
    And the patient should be notified that medication is ready
    And the prescription status should be "READY_FOR_PICKUP"

  Scenario: Complete full preparation workflow
    Given I have located the medication in stock
    When I prepare the medication with lot number "LOT12345" and expiry "2026-12-31"
    And a pharmacist verifies the prescription
    And I finalize the preparation
    Then the prescription status should be "READY_FOR_PICKUP"

  Scenario: Fail to prepare non-existent prescription
    Given a prescription with ID "INVALID" does not exist
    When I attempt to prepare prescription "INVALID"
    Then the preparation should fail
    And the system should return an error message "Prescription not found"

  Scenario: Fail to verify prescription that is not prepared
    Given I am authenticated as a pharmacist
    And the prescription with ID "RX001" has status "CREATED"
    When I attempt to verify the prescription
    Then the verification should fail
    And the system should return an error message "Prescription must be prepared before verification"

  Scenario: Fail to finalize prescription that is not verified
    Given the prescription with ID "RX001" has status "PREPARED"
    When I attempt to finalize the preparation
    Then the finalization should fail
    And the system should return an error message "Prescription must be verified before finalization"

  Scenario: Non-pharmacist cannot verify prescription
    Given I am authenticated as an assistant
    And the prescription with ID "RX001" has status "PREPARED"
    When I attempt to verify the prescription
    Then the verification should fail
    And the system should return an error message "Only pharmacists can verify prescriptions"
