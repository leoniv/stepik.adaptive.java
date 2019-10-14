require 'rspec/core/rake_task'
require 'jruby'

RSpec::Core::RakeTask.new(:spec)

task :hello do
  puts "Hello #{RUBY_PLATFORM}"
end

task :default => :spec
