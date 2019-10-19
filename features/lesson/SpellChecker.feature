Feature: Class SpellChecker
  A simple spell checker which gets all its data from stdin.
  The first line of the input contains d – number of records in the list of
  known word. Next go d lines contain one known word per line, next — the
  number l of lines of the text, after which — l lines of the text.

  Scenario: Spell checker puts erroneous words to stdout
    When I run `run_class lesson.SpellChecker` interactively
    And I type "3"
    And I type "a"
    And I type "bb"
    And I type "cCc"
    And I type "2"
    And I type "a bb aab aba ccc"
    And I type "c bb aaa aab"
    And I close the stdin stream
    Then the output should contain:
    """
    aaa
    aab
    aba
    c
    """
    And the exit status should be 0

  Scenario: Spell checker puts nothing when no errors found
    When I run `run_class lesson.SpellChecker` interactively
    And I type "3"
    And I type "a"
    And I type "bb"
    And I type "cCc"
    And I type "2"
    And I type "a bb ccc"
    And I type "bb "
    And I close the stdin stream
    Then the output should contain exactly:
    """
    """
    And the exit status should be 0
