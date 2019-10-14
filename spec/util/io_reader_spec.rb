java_import 'stepik.adaptive.util.IoReader'

RSpec.describe IoReader do
  describe 'IoReader.scanBy shold scan input by scanFunction' do
    it "IoReader.scanIntegerFunction" do
      expect(IoReader.scanBy('1 b 3'.to_instream, IoReader.scanIntegerFunction).to_a)
        .to eq [1, 3]
    end

    it "IoReader.scanDoubleFunction" do
      expect(IoReader.scanBy '22e-1 3e1'.to_instream, IoReader.scanDoubleFunction)
        .to eq [2.2, 30.0]
    end

    it "IoReader.scanWordsFunction" do
      expect(IoReader.scanBy 'w1 w2 w3'.to_instream, IoReader.scanWordsFunction)
        .to eq %w[w1 w2 w3]
    end
  end
end
