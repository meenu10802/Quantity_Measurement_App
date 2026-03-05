package com.bridgelabz;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class QuantityMeasurementAppTest {

    @Test
    void givenSameFeetValue_ShouldReturnTrue() {
        QuantityMeasurementApp.Feet first = new QuantityMeasurementApp.Feet(1.0);
        QuantityMeasurementApp.Feet second = new QuantityMeasurementApp.Feet(1.0);
        assertTrue(first.equals(second));
    }

    @Test
    void givenDifferentFeetValue_ShouldReturnFalse() {
        QuantityMeasurementApp.Feet first = new QuantityMeasurementApp.Feet(1.0);
        QuantityMeasurementApp.Feet second = new QuantityMeasurementApp.Feet(2.0);
        assertFalse(first.equals(second));
    }

    @Test
    void givenFeetValue_WhenComparedWithNull_ShouldReturnFalse() {
        QuantityMeasurementApp.Feet first = new QuantityMeasurementApp.Feet(1.0);
        assertFalse(first.equals(null));
    }

    @Test
    void givenFeetValue_WhenComparedWithDifferentType_ShouldReturnFalse() {
        QuantityMeasurementApp.Feet first = new QuantityMeasurementApp.Feet(1.0);
        assertFalse(first.equals("1.0"));
    }

    @Test
    void givenSameReference_ShouldReturnTrue() {
        QuantityMeasurementApp.Feet first = new QuantityMeasurementApp.Feet(1.0);
        assertTrue(first.equals(first));
    }
//done
}