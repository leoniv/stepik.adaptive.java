package stepik.adaptive;

import java.util.function.BinaryOperator;
import stepik.adaptive.util.IoReader;

public class Main {
  public static void main(String[] argv) {
    BinaryOperator<String> concat = (x, y) -> x + "\n" + y;
    System.out.println(IoReader.getLines(System.in).reduce(concat).get());
  }
}
