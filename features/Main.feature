Feature: Class Main
  @debudd
  Scenario: Hello Java
    When I run `run_class Main` interactively
    And I type "Hello world\nHello Java"
    And I close the stdin stream
    Then the output should contain "Hello Java"
    And the exit status should be 0
