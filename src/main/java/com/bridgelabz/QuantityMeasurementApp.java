package com.bridgelabz;

public class QuantityMeasurementApp {

    public static void main(String[] args) {

        Quantity<LengthUnit> length1 =
                new Quantity<>(10.0, LengthUnit.FEET);

        Quantity<LengthUnit> length2 =
                new Quantity<>(6.0, LengthUnit.INCH);

        System.out.println("Subtraction: " +
                length1.subtract(length2));

        System.out.println("Division: " +
                length1.divide(new Quantity<>(2.0, LengthUnit.FEET)));

        Quantity<WeightUnit> weight1 =
                new Quantity<>(10.0, WeightUnit.KILOGRAM);

        Quantity<WeightUnit> weight2 =
                new Quantity<>(5000.0, WeightUnit.GRAM);

        System.out.println("Weight subtraction: " +
                weight1.subtract(weight2));

        System.out.println("Weight division: " +
                weight1.divide(new Quantity<>(5.0, WeightUnit.KILOGRAM)));

        Quantity<VolumeUnit> volume1 =
                new Quantity<>(5.0, VolumeUnit.LITRE);

        Quantity<VolumeUnit> volume2 =
                new Quantity<>(500.0, VolumeUnit.MILLILITRE);

        System.out.println("Volume subtraction: " +
                volume1.subtract(volume2));

        System.out.println("Volume division: " +
                volume1.divide(new Quantity<>(1.0, VolumeUnit.LITRE)));
    }
}