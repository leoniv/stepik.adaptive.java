package stepik.adaptive.lesson

import spock.lang.*
import java.util.function.*;

class CustomIntegerReducerSpec extends Specification {
  def getKlass() {
    CustomIntegerReducer
  }

  def "CustomIntegerReducer.reduceIntOperator"() {
    expect:
      assert klass.reduceIntOperator.apply(seed, operator).applyAsInt(*range) == result

    where:
      seed | operator          | range  | result
         4 | (IntBinaryOperator) { x, y -> x + y } | [1, 4] | 14
         4 | (IntBinaryOperator) { x, y -> x * y } | [1, 4] | 96
  }

  def "CustomIntegerReducer.sumOperator"() {
    expect:
      assert klass.sumOperator.applyAsInt(1, 5) == 15
  }

  def "CustomIntegerReducer.productOperator"() {
    expect:
      assert klass.productOperator.applyAsInt(1, 5) == 120
  }
}
