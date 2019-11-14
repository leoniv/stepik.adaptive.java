package stepik.adaptive.lesson;

import java.lang.IllegalArgumentException;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class NumberDecomposition {

  public static void main(String[] argv) {
    try (Scanner scanner = new Scanner(System.in)) {
      System.out.println(new Algorithm(scanner.nextInt()));
    }
  }

  /**
   * This algorithm generates all decomposition natural number n
   * into the whole positive addends in a lexicographical order
   * */
  static class Algorithm {
    public final int n;

    public Algorithm(int n) {
      if (n < 0 || n > 40) {
        throw new IllegalArgumentException(Integer.toString(n));
      }
      this.n = n;
    }

    public int[][] run() {
      return null;
    }

    @Override
    public String toString() {
      return Stream.of(run()).map(
           arr -> IntStream.of(arr)
                    .filter(x -> x > 0)
                    .mapToObj(Integer::toString)
                    .collect(Collectors.joining(" "))
          ).collect(Collectors.joining("\n"));
    }
  }
}
