Feature: Algorithm which decompose a natural number into the whole positive
  addends. The addends should go in non-ascending order within each
  decomposition. Output all decompositions in the lexicographical order.

  Scenario:
    When I run `run_class lesson.NumberDecomposition` interactively
    And I type "5"
    And the output should contain exactly:
    """
    1 1 1 1 1
    2 1 1 1
    2 2 1
    3 1 1
    3 2
    4 1
    5
    """
    Then the exit status should be 0

