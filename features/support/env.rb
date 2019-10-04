require 'pry'
require 'aruba/cucumber'
require 'cucumber/platform'

Before do
  cd '../../'
  next unless Aruba.platform.which('gradle').nil?

  skip_this_scenario
end
