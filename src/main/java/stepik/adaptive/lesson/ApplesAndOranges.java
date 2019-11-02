package stepik.adaptive.lesson;

@SuppressWarnings("unchecked")
abstract class Fruit<T extends Fruit> implements Comparable<T> {

  private final int weigth;

  public Fruit(int weight) {
    this.weigth = weight;
  }

  public int getWeigth() {
    return this.weigth;
  }

  @SuppressWarnings("unchecked")
  public int compareTo(T other) {
    return Integer.compare(this.weigth, ((Fruit<T>) other).weigth);
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

class Test {
  void test() {
   new Apple(10).compareTo(new Orange(0));
  }
}
