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

  def "The illegal invocation Apple.compareTo(Orange other) is working with groovy"() {
    when: "I run illegal comparsation"
      def result = new Apple(100).compareTo(new Orange(100)) == 0

    then: "But groovy can cast Oranges to Apples ;)"
      assert result
  }
}
