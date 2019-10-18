package stepik.adaptive.util

import spock.lang.*
import java.io.*

class IoReaderSpec extends Specification {
  def toInputStream(String str) {
    new ByteArrayInputStream(str.bytes)
  }

  def "IoReader#scanBy() shold parse input by scanFunction"() {
    expect:
      assert IoReader.scanBy(toInputStream(input), scanFunction) == output

    where:
      input            | scanFunction                   | output
      '1 a 2 b 3'      | IoReader.scanIntegerFunction() | [1, 2, 3]
      '1 a 22e-1 b'    | IoReader.scanDoubleFunction()  | [1, 2.2]
      "w1 \t w2 \n w3" | IoReader.scanWordsFunction()   | ['w1', 'w2', 'w3']
      "w1 w2 \n w3"    | IoReader.scanLinesFunction()   | ['w1 w2 ', ' w3']
  }
}
