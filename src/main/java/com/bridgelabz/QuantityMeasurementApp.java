package com.bridgelabz;

public class QuantityMeasurementApp {

    // ---------------- ENUM ----------------
    public enum LengthUnit {

        FEET(1.0),
        INCH(1.0 / 12.0),
        YARDS(3.0),                 // 1 yard = 3 feet
        CENTIMETER(0.393701 / 12.0); // 1 cm = 0.393701 inch → convert to feet

        private final double conversionFactorToFeet;

        LengthUnit(double conversionFactorToFeet) {
            this.conversionFactorToFeet = conversionFactorToFeet;
        }

        public double toBaseUnit(double value) {
            return value * conversionFactorToFeet;
        }
    }

    // ---------------- GENERIC QUANTITY ----------------
    public static class Quantity {

        private final double value;
        private final LengthUnit unit;

        public Quantity(double value, LengthUnit unit) {
            if (unit == null)
                throw new IllegalArgumentException("Unit cannot be null");

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

            double thisInFeet = this.unit.toBaseUnit(this.value);
            double otherInFeet = other.unit.toBaseUnit(other.value);

            return Double.compare(thisInFeet, otherInFeet) == 0;
        }

        @Override
        public int hashCode() {
            double baseValue = unit.toBaseUnit(value);
            return Double.hashCode(baseValue);
        }
    }
}