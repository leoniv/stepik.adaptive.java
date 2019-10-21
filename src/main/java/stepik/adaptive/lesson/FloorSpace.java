package stepik.adaptive.lesson;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;
import java.util.function.Supplier;
import static java.lang.Math.sqrt;

import stepik.adaptive.util.*;

public class FloorSpace {
  public static void main(String[] srgv) {
    List<String>input = IoReader.scannerFor(IoReader.scanWordsFunction()).apply(System.in);
    Shape.Builder shapeBuilder = new Shape.Builder(input.remove(0));
    input.stream().forEach(it -> shapeBuilder.add(it));
    System.out.printf("%.1f", shapeBuilder.build().area());
  }

  interface Area {
    public Double area();
  }

  abstract static class Shape implements Area {
    List<Double> values = new ArrayList<>();

    static class Triangle extends Shape {
      @Override
      public Double area() {
        Double p = (a() + b() + c()) / 2;
        return sqrt(p * (p - a()) * (p - b()) * (p - c()));
      }
    }

    static class Rectangle extends Shape {
      @Override
      public Double area() {
        return a() * b();
      }
    }

    static class Circle extends Shape {
      public final Double Pi = (Double) 3.14;
      @Override
      public Double area() {
        return a() * a() * Pi;
      }
    }

    Double a() { return values.get(0); }
    Double b() { return values.get(1); }
    Double c() { return values.get(2); }

    static class Builder {

      final static Map<String, Supplier<Shape>> KNOWN_SHAPES = Map.of(
        "triangle", Triangle::new,
        "rectangle", Rectangle::new,
        "circle", Circle::new
      );

      private Shape instance;

      public Builder(String shapeType) {
        this.instance = Objects.requireNonNull(
                            KNOWN_SHAPES.get(shapeType.toLowerCase())
                          ).get();
      }

      public Builder add(Double value) {
        this.instance.values.add(value);
        return this;
      }

      public Builder add(String value) {
        return add(Double.valueOf(value));
      }

      public Shape build() {
        return this.instance;
      }
    }
  }
}
