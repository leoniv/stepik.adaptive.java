package stepik.adaptive.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Stream;

public class IoReader {
  public static Stream<String> getLines(InputStream in) {
    return new BufferedReader(new InputStreamReader(in)).lines();
  }
}
