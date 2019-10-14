require "bundler/setup"
require "java"

class String
  java_import 'java.io.ByteArrayInputStream'
  def to_instream
    java.io.ByteArrayInputStream.new(self.to_java_bytes)
  end
end

module RSpec
  module DataDrivenExamples
    def example(message = nil, &block)
      @example_message = message
      @example_block = block
    end

    def where(cols, *rows)
      rows.each do |row|
        example_block = @example_block
        message = []
        cols.each_with_index {|col, i| message << "#{col}: '#{row[i]}'"}
        describe @example_message || "Example #{message.join(', ')}" do
          cols.each_with_index {|col, i| let(col) { row[i] }}
          it &example_block
        end
      end
    end
  end
end

RSpec.configure do |config|
  config.example_status_persistence_file_path = ".rspec_status"

  config.disable_monkey_patching!

  config.expect_with :rspec do |c|
    c.syntax = :expect
  end

  config.extend RSpec::DataDrivenExamples
end
