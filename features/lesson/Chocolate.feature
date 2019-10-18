Feature: Class Chocolate
  A chocolate bar has a shape of rectangle, divided into NxM segments.
  You can break down this chocolate bar into two parts by a single straight
  line (only once). Find whether you can break off exactly K segments from the
  chocolate. Each segment is 1x1.

  The program gets an input of three integers: N, M, K

  The program must output one of the two words: YES or NO

  Scenario: YES answer
    When I run `run_class lesson.Chocolate` interactively
    And I type "4"
    And I type "2"
    And I type "6"
    And I close the stdin stream
    Then the output should contain:
    """
    YES
    """
    And the exit status should be 0

  Scenario: NO answer
    When I run `run_class lesson.Chocolate` interactively
    And I type "2"
    And I type "10"
    And I type "7"
    And I close the stdin stream
    Then the output should contain:
    """
    NO
    """
    And the exit status should be 0

  Scenario: NO answer
    When I run `run_class lesson.Chocolate` interactively
    And I type "3"
    And I type "4"
    And I type "15"
    And I close the stdin stream
    Then the output should contain:
    """
    NO
    """
    And the exit status should be 0
