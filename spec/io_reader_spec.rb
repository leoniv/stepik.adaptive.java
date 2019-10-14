puts "classpath = #{ENV['CLASSPATH']}"
java_import 'stepik.adaptive.util.IoReader'

RSpec.describe IoReader do

  def input
    []
  end

  it "IoReader.scanBy" do
    expect(IoReader.scanBy(input, IoReader.scanIntegerFunction)).should.be []
  end

end
