Feature: Unregister Agent
  As an administrator
  I want to deactivate agent accounts
  So that former employees cannot access the system

  Background:
    Given I am authenticated as an administrator
    And an active agent with username "jdoe" exists

  Scenario: Successfully unregister an agent
    When I unregister the agent with username "jdoe"
    Then the agent unregistration should be successful
    And the agent account should be deactivated
    And the agent should not be able to sign in

  Scenario: Successfully unregister a pharmacist
    Given an active pharmacist with username "pharmacist1" exists
    When I unregister the agent with username "pharmacist1"
    Then the agent unregistration should be successful
    And the agent should be marked as inactive

  Scenario: Fail to unregister non-existent agent
    Given an agent with username "nonexistent" does not exist
    When I attempt to unregister agent "nonexistent"
    Then the agent unregistration should fail
    And the system should return an error message "Agent not found"

  Scenario: Fail to unregister already inactive agent
    Given an inactive agent with username "olduser" exists
    When I attempt to unregister agent "olduser"
    Then the agent unregistration should fail
    And the system should return an error message "Agent is already inactive"

  Scenario: Fail to unregister self
    When I attempt to unregister my own account
    Then the agent unregistration should fail
    And the system should return an error message "Cannot unregister your own account"

  Scenario: Non-administrator cannot unregister agents
    Given I am authenticated as a pharmacist
    When I attempt to unregister an agent
    Then the agent unregistration should fail
    And the system should return an error message "Only administrators can unregister agents"

  Scenario: Audit trail is created for unregistration
    When I unregister the agent with username "jdoe"
    Then the unregistration should be logged with timestamp and administrator username
