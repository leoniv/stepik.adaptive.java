Feature: Class DerivativeOfPolinomial
  The problem is simple – find the derivative of the polynomial.

  Scenario Outline:
    When I run `run_class lesson.DerivativeOfPolinomial` interactively
    And I type "<input>"
    And I close the stdin stream
    Then the output should contain "<out>"
    And the exit status should be 0

    Examples:
      | input               | out            |
      | x^2+x               | 2*x+1          |
      | 2*x^100+100*x^2     | 200*x^99+200*x |
      | x^10000+x+1         | 10000*x^9999+1 |
      | -x^2-x^3            | -3*x^2-2*x     |
      | x+x+x+x-x+x-x+x+x+x | 8              |
