package stepik.adaptive.util;

import static stepik.adaptive.util.IoReader.scanBy;
import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Arrays;

import org.junit.Test;

public class IoReaderTest {
  private static final InputStream scanByInput =
    new ByteArrayInputStream("1 a 2 b 3".getBytes());
  private static final Integer[] expectedInteger = { 1, 2, 3 };

  @Test(timeout = 1000)
   public void csanByItegerScanner() {
    assertEquals(Arrays.asList(expectedInteger),
        scanBy(scanByInput, IoReader.scanIntegerFunction()));
  }
}
