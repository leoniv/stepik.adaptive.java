package stepik.adaptive.lesson

import spock.lang.Specification

class NumberDecompositionSpec extends Specification {

  @Newify
  def "NumberDecomposition.Algorithm"() {
    when:
      def result = NumberDecomposition.Algorithm.new(5).run()

    then:
      assert result == [ [1,1,1,1,1],
                         [2,1,1,1],
                         [2,2,1]
                         [3,1,1]
                         [3,2]
                         [4,1]
                         [5]
                        ]
  }
}
