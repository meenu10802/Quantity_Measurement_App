package com.bridgelabz;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest {

    @Test
    void testLengthEquality_FeetToInch() {

        Quantity<LengthUnit> feet =
                new Quantity<>(1.0, LengthUnit.FEET);

        Quantity<LengthUnit> inch =
                new Quantity<>(12.0, LengthUnit.INCH);

        assertEquals(feet, inch);
    }

    @Test
    void testLengthConversion_FeetToInch() {

        Quantity<LengthUnit> feet =
                new Quantity<>(1.0, LengthUnit.FEET);

        Quantity<LengthUnit> result =
                feet.convertTo(LengthUnit.INCH);

        assertEquals(
                new Quantity<>(12.0, LengthUnit.INCH),
                result
        );
    }

    @Test
    void testLengthAddition() {

        Quantity<LengthUnit> feet =
                new Quantity<>(1.0, LengthUnit.FEET);

        Quantity<LengthUnit> inch =
                new Quantity<>(12.0, LengthUnit.INCH);

        Quantity<LengthUnit> result =
                feet.add(inch, LengthUnit.FEET);

        assertEquals(
                new Quantity<>(2.0, LengthUnit.FEET),
                result
        );
    }

    @Test
    void testWeightEquality_KgToGram() {

        Quantity<WeightUnit> kg =
                new Quantity<>(1.0, WeightUnit.KILOGRAM);

        Quantity<WeightUnit> gram =
                new Quantity<>(1000.0, WeightUnit.GRAM);

        assertEquals(kg, gram);
    }

    @Test
    void testWeightConversion_KgToGram() {

        Quantity<WeightUnit> kg =
                new Quantity<>(1.0, WeightUnit.KILOGRAM);

        Quantity<WeightUnit> result =
                kg.convertTo(WeightUnit.GRAM);

        assertEquals(
                new Quantity<>(1000.0, WeightUnit.GRAM),
                result
        );
    }

    @Test
    void testWeightAddition() {

        Quantity<WeightUnit> kg =
                new Quantity<>(1.0, WeightUnit.KILOGRAM);

        Quantity<WeightUnit> gram =
                new Quantity<>(1000.0, WeightUnit.GRAM);

        Quantity<WeightUnit> result =
                kg.add(gram, WeightUnit.KILOGRAM);

        assertEquals(
                new Quantity<>(2.0, WeightUnit.KILOGRAM),
                result
        );
    }

    @Test
    void testVolumeEquality_LitreToMillilitre() {

        Quantity<VolumeUnit> litre =
                new Quantity<>(1.0, VolumeUnit.LITRE);

        Quantity<VolumeUnit> millilitre =
                new Quantity<>(1000.0, VolumeUnit.MILLILITRE);

        assertEquals(litre, millilitre);
    }

    @Test
    void testVolumeConversion_LitreToMillilitre() {

        Quantity<VolumeUnit> litre =
                new Quantity<>(1.0, VolumeUnit.LITRE);

        Quantity<VolumeUnit> result =
                litre.convertTo(VolumeUnit.MILLILITRE);

        assertEquals(
                new Quantity<>(1000.0, VolumeUnit.MILLILITRE),
                result
        );
    }

    @Test
    void testVolumeAddition() {

        Quantity<VolumeUnit> litre =
                new Quantity<>(1.0, VolumeUnit.LITRE);

        Quantity<VolumeUnit> millilitre =
                new Quantity<>(1000.0, VolumeUnit.MILLILITRE);

        Quantity<VolumeUnit> result =
                litre.add(millilitre, VolumeUnit.LITRE);

        assertEquals(
                new Quantity<>(2.0, VolumeUnit.LITRE),
                result
        );
    }
}