package stepik.adaptive.lesson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.List;
import java.util.Optional;

class  DerivativeOfPolinomial {
  public static void main(String[] args) {
    try (Scanner scanner = new Scanner(System.in)) {
      System.out.println("FIXME");
    }
  }

  static class Polinomial {
    List<Member> members = new ArrayList<>();
    static final String SPLITER = "(?<!\\^)(?=-|\\+)";

    protected Polinomial(List<Member> members) {
      this.members = members;
    }

    public static Polinomial parse(String input) {
      String[] split = input.replaceAll("\\s+", "").split(SPLITER);
      return new Polinomial(
                    Arrays.stream(split)
                      .map(Member::parse)
                      .collect(Collectors.toList())
                  );
    }

    public String toString() {
      return members.stream().map(Member::toString)
        .reduce(
            "", (acc, s) -> acc + (s.startsWith("-") || acc.isEmpty() ? "" : "+") + s
            );
    }

    public Polinomial derivative() {
      return new Polinomial(
                    members.stream()
                    .map(Member::derivative)
                    .collect(Collectors.toList())
                  );
    }

    static class ParseError extends RuntimeException {
      public ParseError(String message) {
        super(message);
      }
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
        final Matcher matcher;
        final String EXPR = "^(-(?=\\d|[a-zA-Z])|\\+(?=\\d|[a-zA-Z]))?(\\d+)?(\\*)?([a-zA-Z]+)?((?<=[a-zA-Z])\\^(-?\\d+))?$";

        Parser(String input) {
          this.input = input.replaceAll("\\s+", "");
          this.matcher = Pattern.compile(EXPR).matcher(input);
          if (!matcher.find()) { throw new ParseError(input); }
        }

        Member parse() {
          return Member.newMember(coefficient(), name(), power());
        }

        Optional<String> match(int group) {
          return Optional.ofNullable(matcher.group(group));
        }

        Integer sign() {
          return Integer.valueOf(match(1).orElse("") + "1");
        }

        Integer coefficient() {
          return sign() * Integer.valueOf(match(2).orElse("1"));
        }

        Integer power() {
          return Integer.valueOf(match(6).orElse("1"));
        }

        String name() {
          return match(4).orElse("");
        }
      }

      public static Member newMember(Integer coefficient,String name, Integer power) {
        if (name.isEmpty() || power == 0) { return new Constant(coefficient); }
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
          if (coefficient + other.coefficient == 0) {
            return new Constant(0);
          }
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
