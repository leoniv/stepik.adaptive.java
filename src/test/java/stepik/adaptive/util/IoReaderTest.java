package stepik.adaptive.util;

import static stepik.adaptive.util.IoReader.scanBy;
import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Scanner;
import java.util.function.Function;

import org.junit.Test;

public class IoReaderTest {
  private static final InputStream scanByInput = new ByteArrayInputStream("1 a 2 b 3".getBytes());
  private static final Integer[] expectedInteger = { 1, 2, 3 };

  @Test(timeout = 1000)
  public void csanByItegerScanner() {
    Function<Scanner, Integer> scanByInteger = s -> {
      if (s.hasNextInt()) {
        return Integer.valueOf(s.nextInt());
      } else {
        s.next();
      };
      return null;
    };
    assertEquals(Arrays.asList(expectedInteger), scanBy(scanByInput, scanByInteger));
  }
}
