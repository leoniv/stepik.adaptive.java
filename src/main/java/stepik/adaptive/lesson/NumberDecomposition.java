package stepik.adaptive.lesson;

import java.lang.IllegalArgumentException;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class NumberDecomposition {

  public static void main(String[] argv) {
    try (Scanner scanner = new Scanner(System.in)) {
      Generator generator = new Generator(scanner.nextInt());
      while (generator.hasNext()) {
        System.out.println(
          IntStream.of(generator.next())
            .filter(X -> X > 0)
            .mapToObj(Integer::toString)
            .collect(Collectors.joining(" "))
        );
      }
    }
  }

  /**
   * This algorithm generates, one by one, all decomposition natural number
   * into the whole positive addends in a lexicographical order
   */
  static class Generator {
    public final int Number;
    private int[] nextSeq;

    public Generator(int number) {
      if (number < 0 || number > 40) {
        throw new IllegalArgumentException("" + number);
      }
      Number = number;
      nextSeq = IntStream.generate(() -> 1).limit((long) number).toArray();
    }

    public boolean hasNext() {
      return nextSeq != null;
    }

    public int[] next() {
      int[] result = nextSeq;
      nextSeq = nextSeqGenerate(result);
      return result;
    }

    int[] nextSeqGenerate(int[] prevSeq) {
      if (prevSeq.length == 0 || prevSeq[0] == Number) { return null; }

      int[] result = new int[Number];

      return result;
    }

    private int sum(int[] arr, int from) {
      return IntStream.of(arr).skip((long) from).sum();
    }

    private int[] distributeSum(int sum, int from, int[] arr) {
      for (int i = from; i < arr.length; i++) {
        if (sum-- > 0) {
          arr[i] = 1;
        } else {
          arr[i] = 0;
        }
      }
      return arr;
    }
  }
}
