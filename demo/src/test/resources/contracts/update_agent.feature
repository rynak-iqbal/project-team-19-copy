Feature: Update Agent
  As an agent
  I want to update my account information
  So that my profile and credentials remain current

  Background:
    Given I am authenticated as an agent with username "jdoe"

  Scenario: Successfully update password
    When I update my password to a new secure password
    Then the update should be successful
    And I should be able to sign in with the new password

  Scenario: Successfully update email address
    When I update my email to "newemail@pharmacy.com"
    Then the update should be successful
    And my email should be "newemail@pharmacy.com"

  Scenario: Successfully update first and last name
    When I update my name to:
      | firstName | Jane |
      | lastName  | Doe  |
    Then the update should be successful

  Scenario: Successfully update multiple fields
    When I update my information:
      | firstName | Jane                  |
      | lastName  | Smith                 |
      | email     | jsmith@pharmacy.com   |
    Then the update should be successful

  Scenario: Force password change on first login
    Given I am a newly registered agent with temporary password
    And I sign in for the first time
    Then I should be prompted to change my password
    And I cannot access other functions until password is changed

  Scenario: Fail to update with weak password
    When I attempt to update my password to "123"
    Then the update should fail
    And the system should return an error message "Password does not meet security requirements"

  Scenario: Fail to update with invalid email format
    When I attempt to update my email to "invalidemail"
    Then the update should fail
    And the system should return an error message "Invalid email format"

  Scenario: Fail to update another agent's information
    Given another agent with username "asmith" exists
    When I attempt to update information for username "asmith"
    Then the update should fail
    And the system should return an error message "Cannot update another agent's information"

  Scenario: Administrator can update agent roles
    Given I am authenticated as an administrator
    And an agent with username "jdoe" exists with role "ASSISTANT"
    When I update the agent's role to "PHARMACIST"
    Then the update should be successful
    And the agent's role should be "PHARMACIST"
