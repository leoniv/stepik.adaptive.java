package stepik.adaptive.lesson;

abstract class Fruit<T extends Fruit<T>> implements Comparable<T> {

  private final int weigth;

  public Fruit(int weight) {
    this.weigth = weight;
  }

  public int getWeigth() {
    return this.weigth;
  }

  public int compareTo(T other) {
    return Integer.compare(this.weigth, other.getWeigth());
  }
}

class Apple extends Fruit<Apple> {

  public Apple(int weight) {
    super(weight);
  }

}

class Orange extends Fruit<Orange> {

  public Orange(int weight) {
    super(weight);
  }
}
