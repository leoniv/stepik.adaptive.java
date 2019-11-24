package stepik.adaptive.lesson;

import java.util.Scanner;
import java.util.EnumMap;

class UnionsOfMeasurement {
  public static void main(String[] args) {
    String input;
    try (Scanner scanner = new Scanner(System.in)) {
      input = scanner.nextLine();
    }
    System.out.printf("%1.2e\n", parseInput(input).amount);
  }

  static UnitAmount parseInput(String inputStr) {
   String[] input = inputStr.split(" ");
   return UnitConverter
     .convert(new UnitAmount(input[1], input[0]))
     .to(Unit.parse(input[3]));
  }

  enum Unit {
    KM, M, CM, MM, MILE, YARD, FOOT, INCH;

    public static Unit parse(String str) {
      return valueOf(str.toUpperCase());
    }
  }

  static class UnitAmount {
    public final Double amount;
    public final Unit unit;

    public UnitAmount(String unit, String amount) {
      this(Unit.parse(unit), Double.valueOf(amount));
    }

    public UnitAmount(Unit unit, Double amount) {
      this.unit = unit;
      this.amount = amount;
    }

    public UnitAmount(Unit unit) {
      this(unit, 1D);
    }
  }

  static class UnitConverter {
    public final UnitAmount fromUnitAmount;
    public final UnitsRate baseRate;
    public static final Unit BASE_UNIT = Unit.MM;
    static final EnumMap<Unit, UnitsRate> unitsBaseRates;
    static {
      unitsBaseRates = new EnumMap<>(Unit.class);
      addUnitBaseRate(Unit.MM, 1D);
      addUnitBaseRate(Unit.CM, 1e1D);
      addUnitBaseRate(Unit.M, 1e3D);
      addUnitBaseRate(Unit.KM, 1e6D);
      addUnitBaseRate(Unit.MILE, 1.609e6D);
      addUnitBaseRate(Unit.YARD, 914.4D);
      addUnitBaseRate(Unit.FOOT, 304.8D);
      addUnitBaseRate(Unit.INCH, 25.4D);
    }

    private static void addUnitBaseRate(Unit unit, Double rate) {
      unitsBaseRates.put(unit, UnitsRate.of(unit, 1D, BASE_UNIT, rate));
    }

    private UnitConverter(UnitAmount fromUnitAmount) {
      this.fromUnitAmount = fromUnitAmount;
      this.baseRate = unitsBaseRates.get(fromUnitAmount.unit);
    }

    public static UnitConverter convert(UnitAmount from) {
      return new UnitConverter(from);
    }

    public UnitAmount to(Unit to) {
      return new UnitAmount(to, toAmount(to));
    }

    public Double toAmount(Unit to) {
      return fromUnitAmount.amount * toRate(to);
    }

    public Double toRate(Unit to) {
      return baseRate.getReverseRate()
        * unitsBaseRates.get(to).getRate();
    }
  }

  static class UnitsRate {
    public final UnitAmount leftUnitAmount;
    public final UnitAmount rightUnitAmount;

    public UnitsRate(UnitAmount left, UnitAmount right) {
      leftUnitAmount = left;
      rightUnitAmount = right;
    }

    public static UnitsRate of(Unit lUnit, Double lAmount,
                               Unit rUnit, Double rAmount) {
      return new UnitsRate(new UnitAmount(lUnit, lAmount)
                          ,new UnitAmount(rUnit, rAmount));
    }

    public Double getRate() {
      return leftUnitAmount.amount / rightUnitAmount.amount;
    }

    public Double getReverseRate() {
      return rightUnitAmount.amount / leftUnitAmount.amount;
    }
  }
}
