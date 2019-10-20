package stepik.adaptive.util;

public class BinUtil {
  public static int settedBitsCount(long number) {
    int result = 0;
    for (int i = 0; i < 64; i++) {
      result += number & 1;
      number >>= 1;
    }
    return result;
  }

  public static int[] settedBitsIndexes(long number) {
    int[] result = new int[settedBitsCount(number)];
    int resultIndex = 0;
    for (int i = 0; i < 64; i++) {
      if (( number & ( (long) 1 << i )) != 0 ) {
        resultIndex = addToArray(result, i, resultIndex);
      }
    }
    return result;
  }

  private static int addToArray(int[] arr, int value, int index) {
    arr[index] = value;
    return ++index;
  }
}
