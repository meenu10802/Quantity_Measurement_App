package com.bridgelabz;

public class QuantityMeasurementApp {

    private static final double EPSILON = 1e-6;

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

            double resultValue = targetUnit.convertFromBaseUnit(sumInBase);

            return new Quantity(resultValue, targetUnit);
        }

        private static double sumInBaseUnit(Quantity q1, Quantity q2) {

            double base1 = q1.unit.convertToBaseUnit(q1.value);
            double base2 = q2.unit.convertToBaseUnit(q2.value);

            return base1 + base2;
        }

        public Quantity convertTo(LengthUnit targetUnit) {

            validateUnit(targetUnit);

            double baseValue = unit.convertToBaseUnit(value);
            double converted = targetUnit.convertFromBaseUnit(baseValue);

            return new Quantity(converted, targetUnit);
        }

        @Override
        public boolean equals(Object obj) {

            if (this == obj)
                return true;

            if (obj == null || getClass() != obj.getClass())
                return false;

            Quantity other = (Quantity) obj;

            double base1 = unit.convertToBaseUnit(value);
            double base2 = other.unit.convertToBaseUnit(other.value);

            return Math.abs(base1 - base2) < EPSILON;
        }

        @Override
        public int hashCode() {
            return Double.hashCode(unit.convertToBaseUnit(value));
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

        double base = source.convertToBaseUnit(value);
        return target.convertFromBaseUnit(base);
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