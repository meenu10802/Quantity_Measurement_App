package com.bridgelabz;

public class QuantityWeight {

    private static final double EPSILON = 1e-6;

    private final double value;
    private final WeightUnit unit;

    public QuantityWeight(double value, WeightUnit unit) {
        validateValue(value);
        validateUnit(unit);
        this.value = value;
        this.unit = unit;
    }

    public double getValue() {
        return value;
    }

    public WeightUnit getUnit() {
        return unit;
    }

    // ----------- EQUALITY -----------
    @Override
    public boolean equals(Object obj) {

        if (this == obj)
            return true;

        if (obj == null || getClass() != obj.getClass())
            return false;

        QuantityWeight other = (QuantityWeight) obj;

        double base1 = unit.convertToBaseUnit(value);
        double base2 = other.unit.convertToBaseUnit(other.value);

        return Math.abs(base1 - base2) < EPSILON;
    }

    @Override
    public int hashCode() {
        return Double.hashCode(unit.convertToBaseUnit(value));
    }

    // ----------- CONVERSION -----------
    public QuantityWeight convertTo(WeightUnit targetUnit) {

        validateUnit(targetUnit);

        double base = unit.convertToBaseUnit(value);
        double converted = targetUnit.convertFromBaseUnit(base);

        return new QuantityWeight(converted, targetUnit);
    }

    // ----------- ADDITION (Implicit Target) -----------
    public QuantityWeight add(QuantityWeight other) {
        return add(other, this.unit);
    }

    // ----------- ADDITION (Explicit Target) -----------
    public QuantityWeight add(QuantityWeight other, WeightUnit targetUnit) {

        if (other == null)
            throw new IllegalArgumentException("Other weight cannot be null");

        validateUnit(targetUnit);

        double base1 = unit.convertToBaseUnit(value);
        double base2 = other.unit.convertToBaseUnit(other.value);

        double sumBase = base1 + base2;

        double resultValue = targetUnit.convertFromBaseUnit(sumBase);

        return new QuantityWeight(resultValue, targetUnit);
    }

    @Override
    public String toString() {
        return String.format("%.6f %s", value, unit);
    }

    private void validateValue(double value) {
        if (!Double.isFinite(value))
            throw new IllegalArgumentException("Invalid numeric value");
    }

    private void validateUnit(WeightUnit unit) {
        if (unit == null)
            throw new IllegalArgumentException("Unit cannot be null");
    }
}