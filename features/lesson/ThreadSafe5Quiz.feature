Feature: Make the following code below thread safe.

  Scenario: Account balance
    When I run `run_class lesson.ThreadSafe5Quiz`
    And the exit status should be 0
    Then the output should match /Account balance is \d+/
