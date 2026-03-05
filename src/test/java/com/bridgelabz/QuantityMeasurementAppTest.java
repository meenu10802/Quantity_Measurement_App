package com.bridgelabz;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class QuantityMeasurementAppTest {

    // ---------------- FEET TESTS ----------------

    @Test
    void givenSameFeetValue_ShouldReturnTrue() {
        assertTrue(QuantityMeasurementApp.areFeetEqual(1.0, 1.0));
    }

    @Test
    void givenDifferentFeetValue_ShouldReturnFalse() {
        assertFalse(QuantityMeasurementApp.areFeetEqual(1.0, 2.0));
    }

    // ---------------- INCH TESTS ----------------

    @Test
    void givenSameInchValue_ShouldReturnTrue() {
        assertTrue(QuantityMeasurementApp.areInchesEqual(1.0, 1.0));
    }

    @Test
    void givenDifferentInchValue_ShouldReturnFalse() {
        assertFalse(QuantityMeasurementApp.areInchesEqual(1.0, 2.0));
    }

    @Test
    void givenInchComparedWithNull_ShouldReturnFalse() {
        QuantityMeasurementApp.Inches inch = new QuantityMeasurementApp.Inches(1.0);
        assertFalse(inch.equals(null));
    }

    @Test
    void givenFeetComparedWithNull_ShouldReturnFalse() {
        QuantityMeasurementApp.Feet feet = new QuantityMeasurementApp.Feet(1.0);
        assertFalse(feet.equals(null));
    }

    @Test
    void givenSameReferenceFeet_ShouldReturnTrue() {
        QuantityMeasurementApp.Feet feet = new QuantityMeasurementApp.Feet(1.0);
        assertTrue(feet.equals(feet));
    }

    @Test
    void givenSameReferenceInch_ShouldReturnTrue() {
        QuantityMeasurementApp.Inches inch = new QuantityMeasurementApp.Inches(1.0);
        assertTrue(inch.equals(inch));
    }
}