Given('I compile project') do
  run_command_and_stop 'gradle classe', fail_on_error: true
end

When("I run class {string} interactively") do |string|
  @class = string
  step 'I compile project'

end
