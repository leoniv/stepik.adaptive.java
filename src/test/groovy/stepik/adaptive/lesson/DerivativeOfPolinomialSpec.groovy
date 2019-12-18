package stepik.adaptive.lesson

import spock.lang.*

class DerivativeOfPolinomialSpec extends Specification {
  static class PolinomialSpec extends DerivativeOfPolinomialSpec {
    def ".parse"() {
      when:
        def p = DerivativeOfPolinomial.Polinomial.parse(input)

      then:
        assert p.members.size() == membersSize

      and:
        assert p.toString() == out

      where:
        input                  | output         | membersSize
        "2*X^2 - 3*x+2"        | "2*x^2-3*x+2"   | 3
        "-2*X^2 + 3*x-2"       | "-2*x^2+3*x-2" | 3
        "-3*X^2 + X^2 + 3*x-2" | "-2*x^2+3*x-2" | 3
    }

    def ".derivative"() {
      when:
        def p = Polinomial.parse(input)

      then:
        assert p.members.size() == membersSize

      and:
        assert p.toString() == out

      where:
        input                               | output
        "2*X^2 - 3*x+2"                     | "4*x-3"
        "-2*X^3 + 3*x-2"                    | "-6*x^2+3"
        "x^2 + x^2 -x^2 -x + x + x + x + 1" | "2x+2"
    }

    static class MemberSpec extends PolinomialSpec {

      def member(coef, name, pow) {
        DerivativeOfPolinomial.Polinomial.Member.newMember(coef, name, pow)
      }

      def parse(input) {
        DerivativeOfPolinomial.Polinomial.Member.parse(input)
      }

      def ".parse"() {
        when:
          def p = DerivativeOfPolinomial.Polinomial.Member.parse(input)

        then:
          assert p.signature() == signature
        and:
          assert p.toString() == toString

        where:
          input    | signature | toString
          "2*X^3"  |  "x^3"     | "2*x^2"
            "2"    |  "^0"      | "2"
        }

      def "#derivative"() {
        expect:
          assert member(coef, "x", pow).derivative().toString() == derivative

        where:
          coef | pow | derivative
           4   | 1   | "4"
           4   | -1  | "-4"
           -4  | 1   | "-4"
           4   | 0   | "0"
           3   | 4   | "12*x^3"
           3   | -4  | "12*x^-5"
      }

      def "#isZero"() {
        expect:
          assert member(coef, "x", 0).isZero() == isZero
          assert member(coef, "x", 1).isZero() == isZero

        where:
          coef | isZero
          0    | true
          1    | false
      }

      def "#sum"() {
        given:
          def p1 = member(coef1, "x", pow)
          def p2 = member(coef2, "x", pow)

        when:
          def sum = p1.sum(p2)

        then:
          sum.coefficient == coef
        and:
          sum.name == "x"
        and:
          sum.power == pow

        where:
          coef1 | coef2 | pow | coef
           2    | -3   | 1 | 1
           2    | -3   | 0 | 1
      }

      def "#toString"() {
        expect:
          member(coef, "x", pow).toString() == toString

        where:
          coef | pow | toString
          1    | 1   | "x"
          -1   | 1   | "-x"
          2    | 1   | "2*x"
          -2   | 1   | "-2*x"
          2    | 3   | "2*x^3"
          2    | -1  | "2*x^-1"
          2    | 0   | "2"
      }

      static class ParserSpec extends DerivativeOfPolinomialSpec {
        def "#parse"() {
          given:
            def parser = DerivativeOfPolinomial.Polinomial
                           .Member.Parser.new(input)

          when:
            def member = parser.parse()

          then:
            assert member.coefficient == coef
          and:
            assert member.power == pow
          and:
            assert member.name == name

          where:
            input    | coef | pow | name
            "2*X^3"  | 2    | 3   | "x"
            "-2*x^2" | -2   | 2   | "x"
              "2"    | 2    | 0   | ""
              "-2"   | -2   | 0   | ""
              "-Y"   | -1   | 1   | "y"
              "X"    | 1    | 1   | "x"
              "z^2"  | 1    | 2   | "z"
              "-x^2" | -1   | 2   | "x"
              "z^-2" | 1    | -2  | "z"
          }
      }
    }
  }
}
