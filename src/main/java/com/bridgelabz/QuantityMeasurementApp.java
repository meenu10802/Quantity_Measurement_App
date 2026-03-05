package com.bridgelabz;

public class QuantityMeasurementApp {

    // ---------------- ENUM ----------------
    public enum LengthUnit {
        FEET(1.0),
        INCH(1.0 / 12.0);

        private final double conversionFactor;

        LengthUnit(double conversionFactor) {
            this.conversionFactor = conversionFactor;
        }

        public double toBaseUnit(double value) {
            return value * conversionFactor;
        }
    }

    // ---------------- GENERIC QUANTITY CLASS ----------------
    public static class Quantity {

        private final double value;
        private final LengthUnit unit;

        public Quantity(double value, LengthUnit unit) {
            if (unit == null) {
                throw new IllegalArgumentException("Unit cannot be null");
            }
            this.value = value;
            this.unit = unit;
        }

        @Override
        public boolean equals(Object obj) {

            if (this == obj)
                return true;

            if (obj == null)
                return false;

            if (getClass() != obj.getClass())
                return false;

            Quantity other = (Quantity) obj;

            double thisInBase = this.unit.toBaseUnit(this.value);
            double otherInBase = other.unit.toBaseUnit(other.value);

            return Double.compare(thisInBase, otherInBase) == 0;
        }

        @Override
        public int hashCode() {
            double baseValue = unit.toBaseUnit(value);
            return Double.hashCode(baseValue);
        }
    }
}