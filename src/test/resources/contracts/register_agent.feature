Feature: Register Agent

  Scenario: Administrator registers a new agent
    Given an administrator is authenticated
    When they provide valid agent information
    Then the system adds the new agent with credentials
