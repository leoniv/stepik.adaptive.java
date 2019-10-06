package stepik.adaptive.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UncheckedIOException;
import java.util.stream.Stream;

public class IoReader {
  public static Stream<String> getLines(InputStream in) {
    BufferedReader reader = new BufferedReader(new InputStreamReader(in));
//    try {
//      return reader.readLine();
//    } catch (IOException e) {
//      throw new UncheckedIOException(e);
//    }
  }
}

