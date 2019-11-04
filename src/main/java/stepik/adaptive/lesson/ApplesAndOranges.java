package stepik.adaptive.lesson;

abstract class Fruit {

  protected final int weigth;

  public Fruit(int weight) {
    this.weigth = weight;
  }

  public int getWeigth() {
    return this.weigth;
  }

  protected <T extends Fruit> int _compareTo(T other) {
    return Integer.compare(this.weigth, other.weigth);
  }
}

class Apple extends Fruit implements Comparable<Apple> {

  public Apple(int weight) {
    super(weight);
  }

  public int compareTo(Apple other) { return _compareTo(other); }

}

class Orange extends Fruit implements Comparable<Orange> {

  public Orange(int weight) {
    super(weight);
  }

  public int compareTo(Orange other) { return _compareTo(other); }

}

class Test {
  void test() {
    Fruit fruitApple = new Apple(30);
    Fruit fruitOrange = new Orange(40);
  //  fruitApple.compareTo(fruitOrange);
  }
}
