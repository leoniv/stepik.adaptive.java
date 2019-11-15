package stepik.adaptive.lesson

import spock.lang.Specification

class NumberDecompositionSpec extends Specification {

  static def generator(n) {
    new NumberDecomposition.Generator(n)
  }

  def "When Number == 0"() {
    given:
      def generator = generator(0)

    and:
      assert generator.hasNext() == true

    when: "it should returns empty array"
      assert generator.next() == []

    then:
      assert generator.hasNext() == false
  }

  def "When Number == 5"() {
    given:
      def generator = generator(5)

    and:
      def expected = [ [1,1,1,1,1],
                       [2,1,1,1,0],
                       [2,2,1,0,0],
                       [3,1,1,0,0],
                       [3,2,0,0,0],
                       [4,1,0,0,0],
                       [5,0,0,0,0] ]

    expect:
      expected.each {expectNext ->
        assert generator.hasNext() == true
        assert generator.next() == expectNext
      }
  }
}
