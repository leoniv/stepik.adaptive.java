package stepik.adaptive.lesson

import static spock.util.matcher.HamcrestMatchers.closeTo
import static spock.util.matcher.HamcrestSupport.that

import spock.lang.*

class UnionsOfMeasurementSpec extends Specification {
  def static Unit = UnionsOfMeasurement.Unit

  def unitAmount(unit, Double amount) {
    new UnionsOfMeasurement.UnitAmount(unit, amount)
  }

  static class MainClasSpec extends UnionsOfMeasurementSpec {
    def "#parseInput"() {
      expect:
        that UnionsOfMeasurement.parseInput(inputStr).amount,
             closeTo(expectedAmount, 0.0000001)

      where:
        inputStr          | expectedAmount
        "10 mile in m"    | 16090
        "10 yard in m"    | 9.144
        "1 foot in cm"    | 30.48
        "10 inch in cm"   | 25.4
        "15.5 km in m"    | 15500
        "10 m in m"       | 10
        "1 cm in m"       | 0.01
        "1 mm in m"       | 0.001
    }
  }

  static class UnitConverterSpec extends UnionsOfMeasurementSpec {
    static UnitConverter = UnionsOfMeasurement.UnitConverter

    def "#Constructor"() {
      given:
        def mile = unitAmount(Unit.MILE, 1);

      when:
        def converter = UnitConverter.convert(mile)

      then:
        assert converter.baseRate.leftUnitAmount.unit == Unit.MILE
        assert converter.baseRate.leftUnitAmount.amount == 1
        assert converter.baseRate.rightUnitAmount.unit == Unit.MM
        assert converter.baseRate.rightUnitAmount.amount == 1609000
        assert converter.fromUnitAmount == mile
    }

    def "#toRate"() {
      given:
        def miles = unitAmount(Unit.MILE, 10)

      when:
        def converter = UnitConverter.convert(miles)

      then:
        assert converter.unitsBaseRates.get(Unit.M).getRate() == 0.001
        assert converter.baseRate.getReverseRate() == 1609000
        assert converter.toRate(Unit.M) == 1609
    }

    def "#toAmount"() {
      given:
        def miles = unitAmount(Unit.MILE, 10)

      when:
        def converter = UnitConverter.convert(miles)

      then:
        assert converter.toAmount(Unit.M) == 16090
    }

    def "#to"() {
      given:
        def miles = unitAmount(Unit.MILE, 10)

      when:
        def converter = UnitConverter.convert(miles)

      then:
        assert converter.to(Unit.parse("m")).unit == Unit.M
        assert converter.to(Unit.M).amount == 16090
    }
  }
}
