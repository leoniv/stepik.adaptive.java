package stepik.adaptive.lesson;

import java.util.function.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

class CustomIntegerReducer {
  /**
   * The operator combines all values in the given range into one value
   * using combiner and initial value (seed)
   */
  public static final BiFunction<Integer, IntBinaryOperator, IntBinaryOperator>
    reduceIntOperator = (x, oper) -> (left, right) -> IntStream
      .rangeClosed(left, right).reduce(x, oper);

  /**
   * The operator calculates the sum in the given range (inclusively)
   */
  public static final IntBinaryOperator sumOperator =
    reduceIntOperator.apply(0, Integer::sum);

  /**
   * The operator calculates the product in the given range (inclusively)
   */
  public static final IntBinaryOperator productOperator =
    reduceIntOperator.apply(1, (x, y) -> x * y);
}
