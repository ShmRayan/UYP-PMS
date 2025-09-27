Feature: Update Agent

  Scenario: Administrator updates agent profile
    Given an administrator selects an existing agent
    When they modify the agent information
    Then the system updates the agent record
