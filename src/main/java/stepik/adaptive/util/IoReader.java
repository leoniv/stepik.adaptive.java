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

    return result;
  }
}
