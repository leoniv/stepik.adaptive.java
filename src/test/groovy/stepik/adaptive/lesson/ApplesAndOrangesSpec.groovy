package stepik.adaptive.lesson

import spock.lang.*

class ApplesAndOrangesSpec extends Specification {

  def "Apple.comppareTo(Apple other)"() {
    expect:
      assert new Apple(100).compareTo(new Apple(100)) == 0
  }

  def "Orange.compareTo(Orange other)"() {
    expect:
      assert new Orange(100).compareTo(new Orange(100)) == 0
  }

  def "Illegal Apple.compareTo(Orange other)"() {
    when: "I run illegal comparsation"
      new Apple(100).compareTo(new Orange(100)) == 0

    then: "Eexception will thrown"
      thrown java.lang.ClassCastException
  }
}
