package stepik.adaptive.lesson;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

public class ComparatorInspectorTest {

  class IntegerComparable implements Comparable<Integer> {
    public int compareTo(Integer other) {
      return 0;
    }
  }

  @SuppressWarnings("rawtypes")
  class LegacyComparable implements Comparable {
    public int compareTo(Object other) {
      return 0;
    }
  }

  @Test
  public void testWithIntegerComparable() {
    assertEquals(
        "java.lang.Integer"
        ,ComparatorInspector
          .getComparatorType(IntegerComparable.class)
          .getTypeName()
    );
  }

  @Test
  public void testWithLegacyComparable() {
    assertNull(
        ComparatorInspector
          .getComparatorType(LegacyComparable.class)
    );
  }
}
