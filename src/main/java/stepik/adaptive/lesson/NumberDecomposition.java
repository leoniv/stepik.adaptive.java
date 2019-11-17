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

    LexicographicalOrder generator = new LexicographicalOrder(number);
    while (generator.hasNextWord()) {
      System.out.println(
        IntStream.of(generator.nextWord())
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

  /*
   * This algorithm generates, one by one, all words length N
   * on the {0, 1, 2, .. N} alphabet in lexicographical order
   * which have summ to equal natural number N
   *
   * Inspired by http://fit.nsu.ru/data_/courses/niu/daio_komb_alg_uchpos.pdf
   */
  static class LexicographicalOrder {
    public final int Number;
    private int[] nextWord;
    private int currentIndex = 0;

    public LexicographicalOrder(int number) {
      if (number < 0 || number > 40) {
        throw new IllegalArgumentException("" + number);
      }
      Number = number;
      nextWord = IntStream.generate(() -> 1).limit((long) number).toArray();
    }

    public boolean hasNextWord() {
      return nextWord != null;
    }

    public int[] nextWord() {
      int[] result = nextWord;
      nextWord = nextWordGenerate(Arrays.copyOf(nextWord, Number));
      return result;
    }

    int[] nextWordGenerate(int[] prevWord) {
      if (prevWord.length == 0 || isLastWord(prevWord) ) { return null; }

      if (sum(prevWord, currentIndex + 1) == 0) { currentIndex = 0; }

      prevWord[currentIndex] += 1;
      currentIndex++;
      distributeSum(sum(prevWord, currentIndex) - 1, currentIndex, prevWord);
      return prevWord;
    }

    boolean isLastWord(int[] word) {
      return word[0] == Number;
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
