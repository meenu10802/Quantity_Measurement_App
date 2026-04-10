package com.bridgelabz;

public class QuantityMeasurementApp {

    public static void main(String[] args) {

        Quantity<TemperatureUnit> t1 =
                new Quantity<>(0.0, TemperatureUnit.CELSIUS);

        Quantity<TemperatureUnit> t2 =
                new Quantity<>(32.0, TemperatureUnit.FAHRENHEIT);

        System.out.println("Temperature Equal: " + t1.equals(t2));

        System.out.println(
                new Quantity<>(100.0, TemperatureUnit.CELSIUS)
                        .convertTo(TemperatureUnit.FAHRENHEIT)
        );

        try {

            new Quantity<>(100.0, TemperatureUnit.CELSIUS)
                    .add(new Quantity<>(50.0, TemperatureUnit.CELSIUS));

        } catch (Exception e) {

            System.out.println(e.getMessage());

        }
    }
}