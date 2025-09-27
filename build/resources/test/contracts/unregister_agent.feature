Feature: Unregister Agent

  Scenario: Administrator unregisters an agent
    Given an administrator is authenticated and an agent exists
    When the administrator confirms unregistering
    Then the system disables the agent account
