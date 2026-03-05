package com.bridgelabz;

public class QuantityMeasurementApp {

    public static void main(String[] args) {

        Quantity<LengthUnit> feet =
                new Quantity<>(1.0, LengthUnit.FEET);

        Quantity<LengthUnit> inches =
                new Quantity<>(12.0, LengthUnit.INCH);

        System.out.println(feet.equals(inches));

        Quantity<LengthUnit> result =
                feet.add(inches, LengthUnit.FEET);

        System.out.println(result);

        Quantity<WeightUnit> kg =
                new Quantity<>(1.0, WeightUnit.KILOGRAM);

        Quantity<WeightUnit> gram =
                new Quantity<>(1000.0, WeightUnit.GRAM);

        System.out.println(kg.equals(gram));
    }
}