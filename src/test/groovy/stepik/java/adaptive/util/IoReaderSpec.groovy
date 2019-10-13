package stepick.java.adaptive.util;

import spock.lang.*;
import java.io.*;

class IoReaderSpec extends Specification {
  def "scan input by scan function"() {
    def input = new ByteArrayInputStream('1 a 2 b 3'.getBytes())
    expect:
    assert 1 == 0
  }
}
