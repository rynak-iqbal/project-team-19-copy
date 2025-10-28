Feature: Register Agent
  As an administrator
  I want to register new pharmacy agents
  So that they can access the system

  Background:
    Given I am authenticated as an administrator

  Scenario: Successfully register a pharmacist
    Given an agent with username "jdoe" does not exist
    When I register an agent with the following information:
      | username   | jdoe              |
      | firstName  | John              |
      | lastName   | Doe               |
      | email      | jdoe@pharmacy.com |
      | role       | PHARMACIST        |
    Then the agent registration should be successful
    And a temporary password should be generated
    And the agent should be prompted to change password on first login

  Scenario: Successfully register an assistant
    Given an agent with username "asmith" does not exist
    When I register an assistant agent:
      | username   | asmith              |
      | firstName  | Alice               |
      | lastName   | Smith               |
      | email      | asmith@pharmacy.com |
      | role       | ASSISTANT           |
    Then the agent registration should be successful

  Scenario: Successfully register another administrator
    Given an agent with username "mjones" does not exist
    When I register an administrator agent:
      | username   | mjones              |
      | firstName  | Mike                |
      | lastName   | Jones               |
      | email      | mjones@pharmacy.com |
      | role       | ADMINISTRATOR       |
    Then the agent registration should be successful

  Scenario: Fail to register agent with duplicate username
    Given an agent with username "jdoe" already exists
    When I attempt to register an agent with username "jdoe"
    Then the agent registration should fail
    And the system should return an error message "Username already exists"

  Scenario: Fail to register agent with invalid email
    When I attempt to register an agent with invalid email "notanemail"
    Then the agent registration should fail
    And the system should return an error message "Invalid email format"

  Scenario: Fail to register agent with missing required information
    When I attempt to register an agent with incomplete information
    Then the agent registration should fail
    And the system should return an error message indicating missing fields

  Scenario: Non-administrator cannot register agents
    Given I am authenticated as a pharmacist
    When I attempt to register an agent
    Then the agent registration should fail
    And the system should return an error message "Only administrators can register agents"
