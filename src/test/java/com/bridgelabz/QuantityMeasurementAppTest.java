package com.bridgelabz;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest {

    private static final double EPSILON = 1e-6;

    // =====================================================
    // UC1–UC8 : LENGTH TESTS
    // =====================================================

    @Test
    void testLengthEquality_SameFeet() {
        var q1 = new QuantityMeasurementApp.Quantity(1.0, LengthUnit.FEET);
        var q2 = new QuantityMeasurementApp.Quantity(1.0, LengthUnit.FEET);

        assertEquals(q1, q2);
    }

    @Test
    void testLengthEquality_FeetToInches() {
        var feet = new QuantityMeasurementApp.Quantity(1.0, LengthUnit.FEET);
        var inches = new QuantityMeasurementApp.Quantity(12.0, LengthUnit.INCH);

        assertEquals(feet, inches);
    }

    @Test
    void testLengthConversion_FeetToInches() {
        var feet = new QuantityMeasurementApp.Quantity(1.0, LengthUnit.FEET);
        var result = feet.convertTo(LengthUnit.INCH);

        assertEquals(12.0, result.getValue(), EPSILON);
    }

    @Test
    void testLengthAddition_SameUnit() {
        var q1 = new QuantityMeasurementApp.Quantity(1.0, LengthUnit.FEET);
        var q2 = new QuantityMeasurementApp.Quantity(2.0, LengthUnit.FEET);

        var result = q1.add(q2);

        assertEquals(3.0, result.getValue(), EPSILON);
    }

    @Test
    void testLengthAddition_CrossUnit() {
        var feet = new QuantityMeasurementApp.Quantity(1.0, LengthUnit.FEET);
        var inches = new QuantityMeasurementApp.Quantity(12.0, LengthUnit.INCH);

        var result = feet.add(inches);

        assertEquals(2.0, result.getValue(), EPSILON);
    }

    @Test
    void testLengthAddition_ExplicitTargetUnit() {
        var feet = new QuantityMeasurementApp.Quantity(1.0, LengthUnit.FEET);
        var inches = new QuantityMeasurementApp.Quantity(12.0, LengthUnit.INCH);

        var result = feet.add(inches, LengthUnit.INCH);

        assertEquals(24.0, result.getValue(), EPSILON);
    }

    @Test
    void testLengthZeroValueEquality() {
        var q1 = new QuantityMeasurementApp.Quantity(0.0, LengthUnit.FEET);
        var q2 = new QuantityMeasurementApp.Quantity(0.0, LengthUnit.INCH);

        assertEquals(q1, q2);
    }

    // =====================================================
    // UC9 : WEIGHT TESTS
    // =====================================================

    @Test
    void testWeightEquality_KgToKg_SameValue() {
        var w1 = new QuantityWeight(1.0, WeightUnit.KILOGRAM);
        var w2 = new QuantityWeight(1.0, WeightUnit.KILOGRAM);

        assertEquals(w1, w2);
    }

    @Test
    void testWeightEquality_KgToKg_DifferentValue() {
        var w1 = new QuantityWeight(1.0, WeightUnit.KILOGRAM);
        var w2 = new QuantityWeight(2.0, WeightUnit.KILOGRAM);

        assertNotEquals(w1, w2);
    }

    @Test
    void testWeightEquality_KgToGram() {
        var kg = new QuantityWeight(1.0, WeightUnit.KILOGRAM);
        var gram = new QuantityWeight(1000.0, WeightUnit.GRAM);

        assertEquals(kg, gram);
    }

    @Test
    void testWeightEquality_GramToKg() {
        var gram = new QuantityWeight(1000.0, WeightUnit.GRAM);
        var kg = new QuantityWeight(1.0, WeightUnit.KILOGRAM);

        assertEquals(gram, kg);
    }

    @Test
    void testWeightEquality_KgToPound() {
        var kg = new QuantityWeight(1.0, WeightUnit.KILOGRAM);
        var pound = new QuantityWeight(2.20462, WeightUnit.POUND);

        assertEquals(kg, pound);
    }

    @Test
    void testWeightEquality_ZeroValue() {
        var kg = new QuantityWeight(0.0, WeightUnit.KILOGRAM);
        var gram = new QuantityWeight(0.0, WeightUnit.GRAM);

        assertEquals(kg, gram);
    }

    @Test
    void testWeightConversion_KgToGram() {
        var kg = new QuantityWeight(1.0, WeightUnit.KILOGRAM);
        var result = kg.convertTo(WeightUnit.GRAM);

        assertEquals(1000.0, result.getValue(), EPSILON);
    }

    @Test
    void testWeightConversion_PoundToKg() {
        var pound = new QuantityWeight(2.20462, WeightUnit.POUND);
        var result = pound.convertTo(WeightUnit.KILOGRAM);

        assertEquals(1.0, result.getValue(), 1e-3);
    }

    @Test
    void testWeightConversion_RoundTrip() {
        var kg = new QuantityWeight(1.5, WeightUnit.KILOGRAM);
        var gram = kg.convertTo(WeightUnit.GRAM);
        var backToKg = gram.convertTo(WeightUnit.KILOGRAM);

        assertEquals(1.5, backToKg.getValue(), EPSILON);
    }

    @Test
    void testWeightAddition_SameUnit() {
        var w1 = new QuantityWeight(1.0, WeightUnit.KILOGRAM);
        var w2 = new QuantityWeight(2.0, WeightUnit.KILOGRAM);

        var result = w1.add(w2);

        assertEquals(3.0, result.getValue(), EPSILON);
    }

    @Test
    void testWeightAddition_CrossUnit_KgPlusGram() {
        var kg = new QuantityWeight(1.0, WeightUnit.KILOGRAM);
        var gram = new QuantityWeight(1000.0, WeightUnit.GRAM);

        var result = kg.add(gram);

        assertEquals(2.0, result.getValue(), EPSILON);
    }

    @Test
    void testWeightAddition_ExplicitTargetUnit() {
        var kg = new QuantityWeight(1.0, WeightUnit.KILOGRAM);
        var gram = new QuantityWeight(1000.0, WeightUnit.GRAM);

        var result = kg.add(gram, WeightUnit.GRAM);

        assertEquals(2000.0, result.getValue(), EPSILON);
    }

    @Test
    void testWeightAddition_NegativeValue() {
        var w1 = new QuantityWeight(5.0, WeightUnit.KILOGRAM);
        var w2 = new QuantityWeight(-2000.0, WeightUnit.GRAM);

        var result = w1.add(w2);

        assertEquals(3.0, result.getValue(), EPSILON);
    }

    @Test
    void testWeightVsLength_Incompatible() {
        var weight = new QuantityWeight(1.0, WeightUnit.KILOGRAM);
        var length = new QuantityMeasurementApp.Quantity(1.0, LengthUnit.FEET);

        assertNotEquals(weight, length);
    }

    @Test
    void testWeightNullComparison() {
        var weight = new QuantityWeight(1.0, WeightUnit.KILOGRAM);

        assertNotEquals(weight, null);
    }

}