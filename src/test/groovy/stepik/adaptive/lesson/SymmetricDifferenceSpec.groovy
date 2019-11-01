package stepik.adaptive.lesson

import spock.lang.*
import java.util.HashSet;

class SymmtricDifferenceSpec extends Specification {

  def toSet(list) {
    return new HashSet(list)
  }

  def klass() {
    SymmtricDifference
  }

  def "SymmtricDifference.symDifference"() {
    expect:
      assert klass().symDifference(toSet(set1), toSet(set2)) == toSet(result)

    where:
      set1            | set2          | result
      []              | [2, 3, 1]     | [1, 2, 3]
      [2, 3, 1]       | []            | [1, 2, 3]
      [2, 7, 0, 3, 5] | [3, 5, 11, 1] | [0, 1, 2, 7, 11]
  }
}
