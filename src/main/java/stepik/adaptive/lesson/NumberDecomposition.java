package stepik.adaptive.lesson;

import java.lang.IllegalArgumentException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class NumberDecomposition {

  public static void main(String[] argv) {
    int number;

    if (argv.length == 0) {
      number = getsInteractiveInput();
    } else {
      number = Integer.valueOf(argv[0]).intValue();
    }

    Generator generator = new Generator(number);
    while (generator.hasNext()) {
      System.out.println(
        IntStream.of(generator.next())
          .filter(X -> X > 0)
          .mapToObj(Integer::toString)
          .collect(Collectors.joining(" "))
      );
    }
  }

  static int getsInteractiveInput() {
    try (Scanner scanner = new Scanner(System.in)) {
      return scanner.nextInt();
    }
  }

  /**
   * This algorithm generates, one by one, all decomposition natural number
   * into the whole positive addends in a lexicographical order
   */
  static class Generator {
    public final int Number;
    private int[] nextSeq;
    private int currentIndex = 0;

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
      nextSeq = nextSeqGenerate(Arrays.copyOf(nextSeq, Number));
      return result;
    }


    int[] nextSeqGenerate(int[] prevSeq) {
      if (prevSeq.length == 0 || prevSeq[0] == Number) { return null; }

      if (sum(prevSeq, currentIndex + 1) == 0) { currentIndex = 0; }

      prevSeq[currentIndex] += 1;
      currentIndex++;
      distributeSum(sum(prevSeq, currentIndex) - 1, currentIndex, prevSeq);
      return prevSeq;
    }

    private int sum(int[] arr, int from) {
      if (from > arr.length - 1) { return 0; }
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
