Feature: Class NumbersFiltering
  You have two IntStream. The first stream contains even numbers and the second
  stream contains odd numbers. Create the third stream that contains numbers
  from both streams which is divisible by 3 and 5.
  After calling collect(Collectors.toList())
  the stream should return sorted list (ascending) of these numbers.
  Two first suitable numbers in the sorted list must be skipped.

  Scenario Outline: interaction
    When I run `run_class lesson.NumbersFiltering` interactively
    And I type "<input>"
    And I close the stdin stream
    Then the output should contain "<output>"
    And the exit status should be 0

  Examples:
      | input       | output   |
      | 1 2 3 4     | []       |
      | 60 90 75 30 | [75, 90] |

