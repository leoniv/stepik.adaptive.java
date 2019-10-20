package stepik.adaptive.util

import spock.lang.*
import stepik.adaptive.util.BinUtil


class BinUtilSpec extends Specification {
  def "BinUtil.settedBitsCount()"() {
    expect:
      BinUtil.settedBitsCount(number) == count

    where:
      number | count
      0      | 0
      1      | 1
      2      | 1
      5      | 2
      7      | 3
      15     | 4
  }

  def "BinUtil.settedBitsIndexes()"() {
    expect:
      BinUtil.settedBitsIndexes(number) == indexes

    where:
      number | indexes
      0      | []
      1      | [0]
      2      | [1]
      3      | [0, 1]
      4      | [2]
      5      | [0, 2]
      6      | [1, 2]
      7      | [0, 1, 2]
      8      | [3]
      9      | [0, 3]
      15     | [0, 1, 2, 3]
  }
}
