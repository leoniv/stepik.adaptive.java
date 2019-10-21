Feature: Class FloorSpace
  Residents of the Malevia country often experiment with the plan of their
  rooms. Rooms can be triangular, rectangular and round. To quickly calculate
  the floorage it is required to write a program, which gets the type of the
  room shape and the relevant parameters as input - the program should output
  the area of the resulting room.

  The value of 3.14 is used instead of the number π in Malevia.

  Scenario Outline:
    When I run `run_class lesson.FloorSpace` interactively
    And I type "<room_shape>"
    And I type "<a>"
    And I type "<b>"
    And I type "<c>"
    And I close the stdin stream
    Then the output should contain "<output>"
    And the exit status should be 0

    Examples:
      | room_shape | a | b | c | output |
      | triangle   | 3 | 4 | 5 |  6.0   |
      | rectangle  | 4 |10 |   |  40.0  |
      | circle     | 5 |   |   |  78.5  |


