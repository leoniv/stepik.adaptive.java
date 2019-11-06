Feature: Class FootballTeams
  Scenario: Game result
    When I run `run_class lesson.FootballTeams` interactively
    And I type "3"
    And I type "Zenit;3;Spartak;1"
    And I type "Spartak;1;CSKA;1"
    And I type "CSKA;0;Zenit;2"
    And I close the stdin stream
    Then the output should contain "Spartak:2 0 1 1 1"
    Then the output should contain "Zenit:2 2 0 0 6"
    Then the output should contain "CSKA:2 0 1 1 1"
    And the exit status should be 0
