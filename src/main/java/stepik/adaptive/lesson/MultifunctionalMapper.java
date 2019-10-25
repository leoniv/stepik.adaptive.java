package stepik.adaptive.lesson;

import java.util.function.*;
import java.lang.Integer;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Arrays;

public class MultifunctionalMapper {

  /**
   * The function accepts a list of mappers and returns an operator that accepts a list of integers
   * and sequentially applies each mapper to each value (perform a transformation)
   */
  public static final Function<List<IntUnaryOperator>, UnaryOperator<List<Integer>>> multifunctionalMapper =
      fs -> xs -> xs.stream()
                    .map(x -> composeAll(fs).applyAsInt(x))
                    .collect(Collectors.toList());

  static IntUnaryOperator composeAll(List<IntUnaryOperator> fs) {
      return fs.stream().reduce(f -> f, (composition, f) -> f.compose(composition));
  }

  /**
   * EXAMPLE: the operator transforms each number to the same number (perform the identity transformation)
   *
   * It returns a list of the same numbers.
   */
  public static final UnaryOperator<List<Integer>> identityTransformation =
          multifunctionalMapper.apply(Arrays.asList(x -> x, x -> x, x -> x));

  /**
   * The operator accepts an integer list.
   * It multiplies by two each integer number and then add one to its.
   *
   * The operator returns transformed integer list.
   */
  public static final UnaryOperator<List<Integer>> multTwoAndThenAddOneTransformation =
    multifunctionalMapper.apply(Arrays.asList(x -> x * 2, x -> x + 1));

  /**
   * The operator accepts an integer list.
   * It squares each integer number and then get the next even number following it.
   *
   * The operator returns transformed integer list.
   */
  public static final UnaryOperator<List<Integer>> squareAndThenGetNextEvenNumberTransformation =
    multifunctionalMapper.apply(Arrays.asList(x -> x * x, x -> getNextEven(x)));

  static Integer getNextEven(Integer number) { return number + (isEven(number) ? 2 : 1); }

  static boolean isEven(Integer x) { return x % 2 == 0; }
}
