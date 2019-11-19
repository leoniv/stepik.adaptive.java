package stepik.adaptive.lesson;

import java.lang.Comparable;
import java.lang.reflect.*;

public class ComparatorInspector {

  /**
   * Return Type variable that corresponds to the type parameterizing Comparator.
   *
   * @param clazz
   *                {@link Class} object, should be non null
   * @return {@link Type} object or null if Comparable does not have type
   *         parameter
   */
public static <T extends Comparable<?>>
              Type getComparatorType(Class<T> clazz) {
    java.lang.reflect.ParameterizedType type = getGenericInterface(clazz, "java.lang.Comparable");
    if (type == null)
      return null;
    if (type.getActualTypeArguments().length == 0)
      return null;
    return type.getActualTypeArguments()[0];
  }

  static java.lang.reflect.ParameterizedType getGenericInterface(Class<? extends Object> clazz, String rawInterfaceType) {
    for (Type type : clazz.getGenericInterfaces()) {
      if (ParameterizedType.class.isInstance(type)
          && ((ParameterizedType) type).getRawType().getTypeName().equals(rawInterfaceType))
      {
        return (java.lang.reflect.ParameterizedType) type;
      }
    }
    return null;
  }
}
