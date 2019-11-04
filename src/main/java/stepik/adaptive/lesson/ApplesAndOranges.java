package stepik.adaptive.lesson;

abstract class Fruit {

  private final int weigth;

  public Fruit(int weight) {
    this.weigth = weight;
  }

  public int getWeigth() {
    return this.weigth;
  }

  protected int _compareTo(Fruit other) {
    return Integer.compare(this.weigth, other.weigth);
  }
}

class Apple extends Fruit implements Comparable<Apple> {

  public Apple(int weight) {
    super(weight);
  }

  public int compareTo(Apple other) { return super._compareTo(other); }

}

class Orange extends Fruit implements Comparable<Orange> {

  public Orange(int weight) {
    super(weight);
  }

  public int compareTo(Orange other) { return super._compareTo(other); }

}
