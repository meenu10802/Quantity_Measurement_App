package com.bridgelabz;

public class QuantityMeasurementApp {

    public static void main(String[] args) {

        Quantity<LengthUnit> feet =
                new Quantity<>(1.0, LengthUnit.FEET);

        Quantity<LengthUnit> inches =
                new Quantity<>(12.0, LengthUnit.INCH);

        System.out.println("Length equality: " + feet.equals(inches));

        Quantity<LengthUnit> lengthResult =
                feet.add(inches, LengthUnit.FEET);

        System.out.println("Length addition: " + lengthResult);


        Quantity<WeightUnit> kg =
                new Quantity<>(1.0, WeightUnit.KILOGRAM);

        Quantity<WeightUnit> gram =
                new Quantity<>(1000.0, WeightUnit.GRAM);

        System.out.println("Weight equality: " + kg.equals(gram));

        Quantity<WeightUnit> weightResult =
                kg.add(gram, WeightUnit.KILOGRAM);

        System.out.println("Weight addition: " + weightResult);


        Quantity<VolumeUnit> litre =
                new Quantity<>(1.0, VolumeUnit.LITRE);

        Quantity<VolumeUnit> millilitre =
                new Quantity<>(1000.0, VolumeUnit.MILLILITRE);

        System.out.println("Volume equality: " + litre.equals(millilitre));

        Quantity<VolumeUnit> volumeResult =
                litre.add(millilitre, VolumeUnit.LITRE);

        System.out.println("Volume addition: " + volumeResult);
    }
}