package stepik.adaptive.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Stream;
import java.util.Scanner;
import java.util.function.Function;
import java.util.ArrayList;
import java.util.List;

public class IoReader {
  public static Stream<String> getLines(InputStream in) {
    return new BufferedReader(new InputStreamReader(in)).lines();
  }

  public static <T> List<T> scanBy(InputStream in, Function<Scanner, ? extends T> scanFunction) {
    List<T> result =  new ArrayList<>();
    Scanner scanner = new Scanner(in);
    while (scanner.hasNext()) {
      T scanned = scanFunction.apply(scanner);
      if (scanned != null) {
        result.add(scanned);
      }
    }
    return result;
  }

  public static Function<Scanner, Integer> scanIntegerFunction() {
     return s -> {
        if (s.hasNextInt()) {
          return Integer.valueOf(s.nextInt());
        } else {
          s.next();
         };
        return null;
      };
  }

  public static Function<Scanner, Double> scanDoubleFunction() {
     return s -> {
        if (s.hasNextDouble()) {
          return Double.valueOf(s.nextDouble());
        } else {
          s.next();
         };
        return null;
      };
  }

  public static Function<Scanner, String> scanWordsFunction() {
     return s -> {
       return s.next();
     };
  }
}
