package stepik.adaptive;

import java.util.function.BinaryOperator;
import static stepik.adaptive.util.IoReader.getLines;

public class Main {
  public static void main(String[] argv) {
    BinaryOperator<String> concat = (x, y) -> x + "\n" + y;
    System.out.println(getLines(System.in).reduce(concat).get());
  }
}
