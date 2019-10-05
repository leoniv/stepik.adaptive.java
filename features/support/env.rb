require 'pry'
require 'aruba/cucumber'
require 'cucumber/platform'

Before do
  check_gradle && gradle(%w[classes])
end

def gradle(args)
  sh("gradle #{args.join ' '}")
end

def check_gradle
  return true unless Aruba.platform.which('gradle').nil?

  skip_this_scenario
end

def sh(cmd)
  result = `#{cmd}`.strip
  raise result unless $?.success?

  result
end
