package stepik.adaptive.lesson;

import spock.lang.*;
import stepik.adaptive.lesson.SpellChecker;

class SpellCheckerSpec extends Specification {

  static class CheckerSpec extends SpellCheckerSpec {

     def "SpellChecker.Checker#spell"() {
       expect:
         assert new SpellChecker.Checker(knowWords).spell(words) == out

       where:
         words                    | knowWords          | out
         ['aA', 'aa', 'bb', 'cc'] | ['aa', 'bB', 'ee'] | ['cc', 'ee']
         ['cc', 'aa', 'cc', 'aa'] | ['Aa', 'bb', 'ee'] | ['cc']
         ['cc', 'ee', 'cc', 'aa'] | ['aa', 'bb', 'ee'] | ['cc', 'cc']
         ['c', 'b', 'e']          | []                 | ['c', 'b', 'c']
         []                       | ['aa', 'bb', 'ee'] | []
     }
  }
}
