package stepik.adaptive.util;

import static stepik.adaptive.util.IoReader.scanBy;
import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.function.Function;

import org.junit.Test;

public class IoReaderTest {
  private InputStream scanByInput = new ByteArrayInputStream("1 a 2 b 3".getBytes());
  private Integer[] expectedInteger = new Integer[] {1, 2, 3};

  @Test
  public void csanByItegerScanner() {
    Function<Scanner, Integer> scanByInteger = s -> Integer.valueOf(s.nextInt());
    assertEquals(expectedInteger, scanBy(scanByInput, scanByInteger));
  }
}
