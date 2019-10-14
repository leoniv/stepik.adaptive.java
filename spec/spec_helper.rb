require "bundler/setup"
require "java"

class String
  java_import 'java.io.ByteArrayInputStream'
  def to_instream
    java.io.ByteArrayInputStream.new(self.to_java_bytes)
  end
end

RSpec.configure do |config|
  config.example_status_persistence_file_path = ".rspec_status"

  config.disable_monkey_patching!

  config.expect_with :rspec do |c|
    c.syntax = :expect
  end
end
