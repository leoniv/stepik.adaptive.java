java_import 'stepik.adaptive.util.IoReader'

RSpec.describe IoReader do
  describe 'IoReader.scanBy shold scan input by scanFunction' do
    example do
      expect(IoReader.scanBy(input.to_instream, scanFunc).to_a).to eq out
    end

    where [:input   , :scanFunc                    , :out        ],
      ['1 a 2 b 3'  , IoReader.scanIntegerFunction , [1, 2, 3   ]],
      ['22e-1 b 3e1', IoReader.scanDoubleFunction  , [2.2, 30.0 ]],
      ['w1 w2 w3'   , IoReader.scanWordsFunction   , %w[w1 w2 w3]]
  end
end
