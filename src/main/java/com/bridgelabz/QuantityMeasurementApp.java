package com.bridgelabz;

public class QuantityMeasurementApp {

    private static final double EPSILON = 1e-6;

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

    public static class Quantity {

        private final double value;
        private final LengthUnit unit;

        public Quantity(double value, LengthUnit unit) {
            validateValue(value);
            validateUnit(unit);
            this.value = value;
            this.unit = unit;
        }

        public double getValue() {
            return value;
        }

        public LengthUnit getUnit() {
            return unit;
        }

        // UC6 (implicit target = first operand unit)
        public Quantity add(Quantity other) {
            return add(other, this.unit);
        }

        // UC7 (explicit target unit)
        public Quantity add(Quantity other, LengthUnit targetUnit) {

            if (other == null)
                throw new IllegalArgumentException("Other quantity cannot be null");

            validateUnit(targetUnit);

            double sumInBase = sumInBaseUnit(this, other);

            double resultValue = targetUnit.fromBaseUnit(sumInBase);

            return new Quantity(resultValue, targetUnit);
        }

        // ---------- PRIVATE UTILITY (DRY) ----------
        private static double sumInBaseUnit(Quantity q1, Quantity q2) {

            double base1 = q1.unit.toBaseUnit(q1.value);
            double base2 = q2.unit.toBaseUnit(q2.value);

            return base1 + base2;
        }

        @Override
        public boolean equals(Object obj) {

            if (this == obj)
                return true;

            if (obj == null || getClass() != obj.getClass())
                return false;

            Quantity other = (Quantity) obj;

            double base1 = unit.toBaseUnit(value);
            double base2 = other.unit.toBaseUnit(other.value);

            return Math.abs(base1 - base2) < EPSILON;
        }

        @Override
        public int hashCode() {
            return Double.hashCode(unit.toBaseUnit(value));
        }

        @Override
        public String toString() {
            return String.format("%.3f %s", value, unit);
        }
    }

    public static double convert(double value,
                                 LengthUnit source,
                                 LengthUnit target) {

        validateValue(value);
        validateUnit(source);
        validateUnit(target);

        double base = source.toBaseUnit(value);
        return target.fromBaseUnit(base);
    }

    private static void validateValue(double value) {
        if (!Double.isFinite(value))
            throw new IllegalArgumentException("Invalid numeric value");
    }

    private static void validateUnit(LengthUnit unit) {
        if (unit == null)
            throw new IllegalArgumentException("Unit cannot be null");
    }
}