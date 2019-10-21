package stepik.adaptive.lesson;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import stepik.adaptive.util.IoReader;

public class NumbersFiltering {

  public static void main(String[] argv) {
    List<Integer> input = IoReader
                          .scannerFor(IoReader.scanIntegerFunction())
                          .apply(System.in);
    System.out.println(
      Arrays.toString(
        createFilteringStream(
           input.stream().filter( it -> (it & 1) == 0 )
             .mapToInt(Integer::intValue)
          ,input.stream().filter( it -> (it & 1) != 0 )
             .mapToInt(Integer::intValue)
          ).mapToObj(Integer::valueOf)
          .collect(Collectors.toList()).toArray()
      )
    );
  }

  public static IntStream createFilteringStream(IntStream evenStream, IntStream oddStream) {
    IntStream result = IntStream.concat(evenStream, oddStream);
    return result
            .filter( it -> (it % 3 == 0) && (it % 5 == 0) )
            .sorted()
            .skip(2);
  }
}
