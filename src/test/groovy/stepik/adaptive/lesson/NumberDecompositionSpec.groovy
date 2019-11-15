package stepik.adaptive.lesson

import spock.lang.Specification

class NumberDecompositionSpec extends Specification {

  static def generator(n) {
    new NumberDecomposition.Generator(n)
  }

  static class InterfaceSpec extends NumberDecompositionSpec {
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

  static class HelpersSpec extends NumberDecompositionSpec {

    def "#sum"() {
      expect:
        assert generator(0).sum((int[])[1,2,3,4,5,6], 3) == 15
    }

    def "#distributeSum"() {
       expect:
         assert generator(0).distributeSum(sum, from, array) == outArray

       where:
        sum | from | array       | outArray
        3   | 1    | (int[]) [9,9,9,9] | [9,1,1,1]
        3   | 0    | (int[]) [9,9,9,9,9] | [1,1,1,0,0]
    }
  }
}
