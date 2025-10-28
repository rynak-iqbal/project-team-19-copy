Feature: Pick Up Medicine
  As a pharmacist
  I want to record medication pickup by patients
  So that prescription fulfillment is properly tracked

  Background:
    Given I am authenticated as a pharmacist
    And a prescription with ID "RX001" exists with status "READY_FOR_PICKUP"

  Scenario: Successfully record pickup with counseling for first-time prescription
    When I record the pickup with counseling:
      | prescriptionId    | RX001                                      |
      | counselingProvided| true                                       |
      | counselingSummary | Discussed dosage, side effects, and timing |
    Then the pickup should be successful
    And the prescription status should be "PICKED_UP"
    And the counseling summary should be recorded

  Scenario: Successfully record pickup without counseling for refill
    When I record the pickup without counseling:
      | prescriptionId    | RX001 |
      | counselingProvided| false |
    Then the pickup should be successful
    And the prescription status should be "PICKED_UP"

  Scenario: Record pickup with patient questions addressed
    When I record the pickup with counseling summary "Answered questions about food interactions"
    Then the pickup should be successful
    And the counseling details should be saved

  Scenario: Fail to record pickup for prescription not ready
    Given a prescription with ID "RX002" has status "PREPARED"
    When I attempt to record pickup for prescription "RX002"
    Then the pickup should fail
    And the system should return an error message "Prescription is not ready for pickup"

  Scenario: Fail to record pickup for non-existent prescription
    Given a prescription with ID "INVALID" does not exist
    When I attempt to record pickup for prescription "INVALID"
    Then the pickup should fail
    And the system should return an error message "Prescription not found"

  Scenario: Fail to record pickup for already picked up prescription
    Given a prescription with ID "RX001" has status "PICKED_UP"
    When I attempt to record pickup for prescription "RX001"
    Then the pickup should fail
    And the system should return an error message "Prescription has already been picked up"

  Scenario: Non-pharmacist cannot record pickup
    Given I am authenticated as an assistant
    When I attempt to record pickup for prescription "RX001"
    Then the pickup should fail
    And the system should return an error message "Only pharmacists can record medication pickup"
