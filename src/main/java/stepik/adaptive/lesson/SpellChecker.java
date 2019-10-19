package stepik.adaptive.lesson;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import stepik.adaptive.util.IoReader;

public class SpellChecker {
  public static void main(String[] argv) {
    Input input = Input.parse(System.in);
    input.lines.stream().flatMap(
          line -> new Checker(input.knownWords).spell(line).stream()
          ).sorted().distinct().forEach(s -> System.out.println(s));
  }

  static class Input {
    List<String> knownWords;
    List<String> lines;

    static Input parse(InputStream inputStream) {
      return parseLines(
          IoReader.scannerFor(IoReader.scanLinesFunction()).apply(inputStream)
          );
    }

    private static Input parseLines(List<String> inputLines) {
      Integer kwCount = Integer.decode(inputLines.get(0));
      List<String> knownWords = inputLines.subList(1, kwCount + 1);
      List<String> lines = inputLines.subList(kwCount + 2, inputLines.size());
      return new Input(lines, knownWords);
    }

    private Input(List<String> lines, List<String> knownWords) {
      this.lines = lines;
      this.knownWords = knownWords;
    }
  }

  static class Checker {
    private HashSet<String> knownWords;

    public Checker(List<String> knownWords) {
      this.knownWords = new HashSet<String>(knownWords.size());
      knownWords.forEach(s-> this.knownWords.add(s.toLowerCase()));
    }

    public List<String> spell(List<String> words) {
      return words.stream()
               .filter(s -> ! this.knownWords.contains(s.toLowerCase()))
               .collect(Collectors.toList());
    }

    public List<String> spell(String str) {
      return spell(words(str));
    }

    public static List<String> words(String str) {
      return IoReader.scannerFor(IoReader.scanWordsFunction())
        .apply(new ByteArrayInputStream(str.getBytes()));
    }
  }
}
