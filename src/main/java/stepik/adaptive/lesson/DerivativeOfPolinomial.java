package stepik.adaptive.lesson;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.List;

class  DerivativeOfPolinomial {
  public static void main(String[] args) {
    try (Scanner scanner = new Scanner(System.in)) {
      System.out.println("FIXME");
    }
  }

  static class Polinomial {
    List<Member> members = new ArrayList<>();

    protected Polinomial(List<Member> members) {
      this.members = normalize(members);
    }

    private static List<Member> normalize(List<Member> members) {
      return members.stream().filter(x -> ! x.isZero())
        .collect(Collectors.toList());
    }

    public static Polinomial parse(String input) {
      return new Polinomial(null);
    }

    public String toString() {
      return members.stream().map(Member::toString)
        .collect(Collectors.joining("+"));
    }

    public Polinomial derivative() {
      return new Polinomial(
                    members.stream()
                    .map(Member::derivative)
                    .collect(Collectors.toList())
                  );
    }

    static abstract class Member {
      final Integer power;
      final String name;
      final Integer coefficient;

      protected Member(Integer  coefficient, String name, Integer power) {
        this.coefficient = coefficient;
        this.name = name.toLowerCase();
        this.power = power;
      }

      static class Parser {
        final String input;

        Parser(String input) {
          this.input = input;
        }

        Member parse() {
          return Member.newMember(coefficient(), name(), power());
        }

        Integer coefficient() {
          return 0; //FIXME
        }

        Integer power() {
          return 0; //FIXME
        }

        String name() {
          return null; //FIXME
        }
      }

      public static Member newMember(Integer coefficient,String name, Integer power) {
        if (power == 0) { return new Constant(coefficient); }
        return new Indeterminate(coefficient, name, power);
      }

      public static Member parse(String string) {
        return new Parser(string).parse();
      }

      public String signature() {
        return name + "^" + power;
      }

      public abstract String toString();

      public boolean isZero() {
        return coefficient == 0;
      }

      public abstract Member derivative();

      public Member sum(Member other) {
        validate(other);
        return sumInner(other);
      }

      protected abstract Member sumInner(Member other);

      private void validate(Member other) {
        if (! signature().equals(other.signature())) {
          throw new IllegalArgumentException(
              signature() + " uncompatable with " + other.signature()
              );
        }
      }

      static class Indeterminate extends Member {

        protected Indeterminate(Integer coefficient, String name, Integer power) {
          super(coefficient, name, power);
        }

        @Override
        public Member derivative() {
          return Member.newMember(coefficient * power, name, power - 1);
        }

        @Override
        public String toString() {
          return coefficientToString()
            + name + (power == 1 ? "" : "^" + power);
        }

        private String coefficientToString() {
          if (coefficient == 1) { return ""; }
          if (coefficient == -1) { return "-"; }
          return coefficient + "*";
        }

        @Override
        protected Member sumInner(Member other) {
          return new Indeterminate(
              coefficient + other.coefficient, name, power
              );
        }
      }

      static class Constant extends Member {
        protected Constant(Integer coefficient) {
          super(coefficient, "", 0);
        }

        @Override
        public Member derivative() {
          return new Constant(0);
        }

        @Override
        public String toString() {
          return coefficient.toString();
        }

        @Override
        protected Member sumInner(Member other) {
          return new Constant(coefficient + other.coefficient);
        }
      }
    }
  }
}
