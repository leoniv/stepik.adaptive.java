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
    private int currentIndex;

    public LexicographicalOrder(int number) {
      if (number < 1) {
        throw new IllegalArgumentException("" + number);
      }
      Number = number;
      nextWord = distributeSum(Number, 0, new int[Number]);
      currentIndex = 0;
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

      currentIndex = findLeftBound(prevWord, findRightBound(prevWord, currentIndex) - 1);
      prevWord[currentIndex] += 1;

      return distributeSum(sum(prevWord, currentIndex + 1) - 1, currentIndex + 1, prevWord);
    }

    int findRightBound(int[] word, int fromIndex) {
      while (! isRightBound(word, fromIndex)) { ++fromIndex; }
      return fromIndex;
    }

    int findLeftBound(int[] word, int fromIndex) {
      while (! isLeftBound(word, fromIndex)) { --fromIndex; }
      return fromIndex;
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

    private int sum(int[] word, int fromIndex) {
      if (fromIndex > word.length - 1) { return 0; }
      return IntStream.of(word).skip((long) fromIndex).sum();
    }

    private int[] distributeSum(int sum, int fromIndex, int[] word) {
      for (int i = fromIndex; i < word.length; i++) {
        if (sum-- > 0) {
          word[i] = 1;
        } else {
          word[i] = 0;
        }
      }
      return word;
    }
  }
}
