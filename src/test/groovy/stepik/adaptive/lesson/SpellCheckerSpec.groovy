package stepik.adaptive.lesson;

import java.io.ByteArrayInputStream
import spock.lang.*
import stepik.adaptive.lesson.SpellChecker

class SpellCheckerSpec extends Specification {

  static class CheckerSpec extends SpellCheckerSpec {

     @Unroll
     def "SpellChecker.Checker#spell #words #knowWords"() {
       expect:
         assert new SpellChecker.Checker(knowWords).spell(words) == out
         assert new SpellChecker.Checker(knowWords)
           .spell(words.join(' ')) == out

       where:
         words                    | knowWords          | out
         ['aA', 'aa', 'bb', 'cc'] | ['aa', 'bB', 'ee'] | ['cc']
         ['cc', 'aa', 'cc', 'aa'] | ['Aa', 'bb', 'ee'] | ['cc', 'cc']
         ['cc', 'ee', 'cc', 'aa'] | ['aa', 'bb', 'ee'] | ['cc', 'cc']
         ['c', 'b', 'e']          | []                 | ['c', 'b', 'e']
         []                       | ['aa', 'bb', 'ee'] | []
     }

     def "SpellChecker.Input.parse()"() {
       given:
         def parser = SpellChecker.Input

       when:
         def parsed = parser.parse(input)

       then:
         assert parsed.knownWords == ['aa', 'bb', 'cc']
         assert parsed.lines == ['aa bb cc', 'bb cc dd', 'cc dd ee']

       where:
         input = new ByteArrayInputStream(
           '''
           3
           aa
           bb
           cc
           3
           aa bb cc
           bb cc dd
           cc dd ee
           '''.trim().split("\n").collect({ it.trim() }).join("\n").bytes
         )
     }
  }
}
