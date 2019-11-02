package stepik.adaptive.lesson;

abstract class Fruit<T extends Fruit> implements Comparable<T> {

  protected final int weigth;

  public Fruit(int weight) {
    this.weigth = weight;
  }

  public int getWeigth() {
    return this.weigth;
  }

  public int compareTo(T other) {
    return Integer.compare(this.weigth, other.weigth);
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
    Fruit fruitApple = new Apple(30);
    Fruit fruitOrange = new Orange(40);
    fruitApple.compareTo(fruitOrange);
  }
}
