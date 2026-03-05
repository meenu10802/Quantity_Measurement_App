package com.bridgelabz;

/**
 * QuantityMeasurementApp
 * Provides length comparison and unit conversion functionality.
 */
public class QuantityMeasurementApp {

    private static final double EPSILON = 1e-6;

    // ---------------- ENUM ----------------
    public enum LengthUnit {

        FEET(1.0),
        INCH(1.0 / 12.0),
        YARDS(3.0),
        CENTIMETER(0.393701 / 12.0);

        private final double conversionFactorToFeet;

        LengthUnit(double conversionFactorToFeet) {
            this.conversionFactorToFeet = conversionFactorToFeet;
        }

        public double toBaseUnit(double value) {
            return value * conversionFactorToFeet;
        }

        public double fromBaseUnit(double baseValue) {
            return baseValue / conversionFactorToFeet;
        }
    }

    // ---------------- QUANTITY CLASS ----------------
    public static class Quantity {

        private final double value;
        private final LengthUnit unit;

        public Quantity(double value, LengthUnit unit) {
            validateValue(value);
            validateUnit(unit);
            this.value = value;
            this.unit = unit;
        }

        public Quantity convertTo(LengthUnit targetUnit) {
            validateUnit(targetUnit);

            double baseValue = unit.toBaseUnit(value);
            double converted = targetUnit.fromBaseUnit(baseValue);

            return new Quantity(converted, targetUnit);
        }

        @Override
        public boolean equals(Object obj) {

            if (this == obj)
                return true;

            if (obj == null || getClass() != obj.getClass())
                return false;

            Quantity other = (Quantity) obj;

            double thisBase = unit.toBaseUnit(value);
            double otherBase = other.unit.toBaseUnit(other.value);

            return Math.abs(thisBase - otherBase) < EPSILON;
        }

        @Override
        public String toString() {
            return value + " " + unit;
        }
    }

    // ---------------- STATIC CONVERT API ----------------
    public static double convert(double value,
                                 LengthUnit source,
                                 LengthUnit target) {

        validateValue(value);
        validateUnit(source);
        validateUnit(target);

        double baseValue = source.toBaseUnit(value);
        return target.fromBaseUnit(baseValue);
    }

    // ---------------- PRIVATE VALIDATION METHODS ----------------
    private static void validateValue(double value) {
        if (!Double.isFinite(value))
            throw new IllegalArgumentException("Invalid numeric value");
    }

    private static void validateUnit(LengthUnit unit) {
        if (unit == null)
            throw new IllegalArgumentException("Unit cannot be null");
    }

    // ---------------- OVERLOADED DEMO METHODS ----------------
    public static double demonstrateLengthConversion(double value,
                                                     LengthUnit from,
                                                     LengthUnit to) {
        return convert(value, from, to);
    }

    public static Quantity demonstrateLengthConversion(Quantity quantity,
                                                       LengthUnit to) {
        return quantity.convertTo(to);
    }
}