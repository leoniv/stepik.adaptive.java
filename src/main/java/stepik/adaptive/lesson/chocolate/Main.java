package stepik.adaptive.lesson.chocolate;

import stepik.adaptive.util.IoReader;
import java.util.List;

public class Main {
  public static void main(String[] argv) {
    final String YES = "YES";
    final String NO = "NO";

    List<Integer> input = IoReader.scanBy(System.in, IoReader.scanIntegerFunction());

      if (breakeable(input.get(0), input.get(1), input.get(2))) {
        System.out.println(YES);
      } else {
        System.out.println(NO);
      }
  }

  static boolean breakeable(Integer n, Integer m, Integer k) {
    return (k <= n * m) && (k % n == 0) || (k % m == 0);
  }
}
