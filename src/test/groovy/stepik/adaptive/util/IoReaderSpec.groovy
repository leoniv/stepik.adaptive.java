package stepik.adaptive.util

import spock.lang.*
import java.io.*

class IoReaderSpec extends Specification {

  def toInputStream(String str) {
    new ByteArrayInputStream(str.bytes)
  }

  def testCases() {
    [
      ['1 a 2 b 3'      , IoReader.scanIntegerFunction() , [1, 2, 3]],
      ['1 a 22e-1 b'    , IoReader.scanDoubleFunction()  , [1, 2.2]],
      ["w1 \t w2 \n w3" , IoReader.scanWordsFunction()   , ['w1', 'w2', 'w3']],
      ["w1 w2 \n w3"    , IoReader.scanLinesFunction()   , ['w1 w2 ', ' w3']]
    ].collect { it[0] = toInputStream(it[0]); it }
  }

  def "IoReader.scanBy() shold parse input by scanFunction"() {

    expect: IoReader.scanBy(input, scanFunction) == output

    where:
      [input ,scanFunction, output] << testCases()
  }

  def """IoReader.scannerFor() applays scanFunction and
      returns lambda which can scanning the input stream"""() {

    expect: IoReader.scannerFor(scanFunction).apply(input) == output

    where:
      [input ,scanFunction, output] << testCases()
  }
}
