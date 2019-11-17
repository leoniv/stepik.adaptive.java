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
    private int index;

    public LexicographicalOrder(int number) {
      if (number < 1 || number > 40) {
        throw new IllegalArgumentException("" + number);
      }
      Number = number;
      nextWord = distributeSum(Number, 0, new int[Number]);
      index = 0;
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
      if (isLastWord(prevWord)) { return null; }

      if (isRightBound(prevWord, index)) {
        index = findLeftBound(prevWord, index - 1);
      }

      prevWord[index] += 1;
      distributeSum(sum(prevWord, index + 1) - 1, index + 1, prevWord);

      if (isRightBound(prevWord, index)) {
        index  = findLeftBound(prevWord, index - 1);
      } else {
        index++;
      }

      return prevWord;
    }

    int findLeftBound(int[] word, int index) {
      while (! isLeftBound(word, index)) { --index; }
      return index;
    }

    boolean isLeftBound(int[] word, int index) {
      return index <= 0 || word[index - 1] > word[index];
    }

    boolean isRightBound(int[] word, int index) {
      return index == word.length - 1 || word[index + 1] == 0;
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
