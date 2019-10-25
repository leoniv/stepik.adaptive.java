package stepik.adaptive.lesson

import spock.lang.Specification
import java.util.List
import java.util.function.IntUnaryOperator

class MultifunctionalMapperSpec extends Specification {

  def klass() { MultifunctionalMapper }
  def "Prop multifunctionalMapper should returns mappers composition"() {

    expect:
      assert klass().multifunctionalMapper.apply(mappers).apply(input) == output

    where:
      mappers | input | output
      [(IntUnaryOperator){x -> x + 2}, (IntUnaryOperator){x -> x * 3}] | [1, 2, 3] | [9, 12, 15]
  }

  def "Prop squareAndThenGetNextEvenNumberTransformation"() {
    expect:
      assert klass().squareAndThenGetNextEvenNumberTransformation.apply([3, 4, 5]) == [10, 18, 26]
  }

  def "Prop multTwoAndThenAddOneTransformation"() {
    expect:
      assert klass().multTwoAndThenAddOneTransformation.apply([1, 2, 3]) == [3, 5, 7]
  }

  def ".getNextEven"() {
    expect:
      assert klass().getNextEven(input) == output

    where:
      input | output
      1     | 2
      2     | 4
      3     | 4
      4     | 6
  }

}
