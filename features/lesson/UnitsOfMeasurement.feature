Feature: class UnionsOfMeasurement
  You should write a program that can transform some units of measurement
  into others.
  The following transformations should be supported:
      miles (1 mile = 1609 m),
      yards (1 yard = 0.9144 m),
      feet (1 foot = 30.48 cm),
      inches (1 inch = 2.54 cm),
      kilometres (1 km = 1000 m),
      meters (m),
      centimetres (1 cm = 0.01 m)
      millimetres (1 mm = 0.001 m)
  Input format:
  Single line in the following format:
  <number> <unit_from> in <unit_to>
  Output format:
  Real number in scientific format (exponential), with an accuracy of exactly
  two digits after decimal point

  Scenario:
    When I run `run_class lesson.UnionsOfMeasurement` interactively
    And I type "15.5 mile in km"
    And I close the stdin stream
    Then the output should match:
    """
    2(\.|,)49e\+01
    """
    And the exit status should be 0
