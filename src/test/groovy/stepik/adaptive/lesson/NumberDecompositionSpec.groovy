package stepik.adaptive.lesson

import spock.lang.Specification

class NumberDecompositionSpec extends Specification {

  static def generator(n) {
    new NumberDecomposition.LexicographicalOrder(n)
  }

  static class InterfaceSpec extends NumberDecompositionSpec {
    def "When Number == 1"() {
      given:
        def generator = generator(1)

      and:
        assert generator.hasNextWord() == true

      when: "it should returns array"
        assert generator.nextWord() == [1]

      then:
        assert generator.hasNextWord() == false
    }

    def "When Number == 7"() {
      given:
        def generator = generator(7)

      and:
        def expected = [ [1,1,1,1,1,1,1],
                         [2,1,1,1,1,1,0],
                         [2,2,1,1,1,0,0],
                         [2,2,2,1,0,0,0],
                         [3,1,1,1,1,0,0],
                         [3,2,1,1,0,0,0],
                         [3,2,2,0,0,0,0],
                         [3,3,1,0,0,0,0],
                         [4,1,1,1,0,0,0],
                         [4,2,1,0,0,0,0],
                         [4,3,0,0,0,0,0],
                         [5,1,1,0,0,0,0],
                         [5,2,0,0,0,0,0],
                         [6,1,0,0,0,0,0],
                         [7,0,0,0,0,0,0] ]

      expect:
        expected.each {expectNext ->
          assert generator.hasNextWord() == true
          assert generator.nextWord() == expectNext
        }
    }

    def getLengthOf(n) {
      def generator = generator(n)
      def result = []
      while (generator.hasNextWord()) {
        result << generator.nextWord()
      }
      result.size()
    }

    def "Length of decompositions"() {
      expect:
        assert getLengthOf(n) == out

        where:
          n  | out
          1  | 1
          2  | 2
          3  | 3
          4  | 5
          5  | 7
          6  | 11
          7  | 15
          8  | 22
          9  | 30
          10 | 42
          20 | 627
    }
  }

  static class HelpersSpec extends NumberDecompositionSpec {

    def "#sum"() {
      expect:
        assert generator(1).sum((int[])[1,2,3,4,5,6], 3) == 15
        assert generator(1).sum((int[])[], 0) == 0
    }

    def "#distributeSum"() {
       expect:
         assert generator(1).distributeSum(sum, from, array) == outArray

       where:
        sum | from | array               | outArray
        3   | 1    | (int[]) [9,9,9,9]   | [9,1,1,1]
        3   | 0    | (int[]) [9,9,9,9,9] | [1,1,1,0,0]
    }

    def "#isRightBound"() {
      expect:
        assert generator(2).isRightBound(arr, index) == out

      where:
        arr              | index | out
        (int[])[1,2,3,0] | 0     | false
        (int[])[1,2,3,0] | 1     | false
        (int[])[1,2,3,0] | 2     | true
        (int[])[1,2,3,1] | 2     | false
        (int[])[1,2,3,0] | 3     | true
    }

    def "#isLeftBound"() {
      expect:
        assert generator(2).isLeftBound(arr, index) == out

      where:
        arr              | index | out
        (int[])[2,2,1,1] | 0     | true
        (int[])[2,2,1,1] | 1     | false
        (int[])[2,2,1,1] | 2     | true
        (int[])[2,2,1,1] | 3     | false
    }

    def "#findLeftBound"() {
      expect:
        assert generator(1).findLeftBound(arr, index) == out

      where:
        arr                | index | out
        (int[])[3,3,2,2,1] | 4     | 4
        (int[])[3,3,2,2,1] | 3     | 2
        (int[])[3,3,2,2,1] | 1     | 0
        (int[])[3,3,3,3,3] | 4     | 0
        (int[])[3,3,3,3,3] | 0     | 0
    }
  }
}
