package com.bridgelabz;

import java.util.Objects;
import java.util.function.DoubleBinaryOperator;

public class Quantity<U extends IMeasurable> {

    private final double value;
    private final U unit;

    private static final double EPSILON = 0.0001;

    public Quantity(double value, U unit) {

        if (unit == null)
            throw new IllegalArgumentException("Unit cannot be null");

        if (!Double.isFinite(value))
            throw new IllegalArgumentException("Invalid numeric value");

        this.value = value;
        this.unit = unit;
    }

    public Quantity<U> convertTo(U targetUnit) {

        if (targetUnit == null)
            throw new IllegalArgumentException("Target unit cannot be null");

        double baseValue = unit.convertToBaseUnit(value);
        double converted = targetUnit.convertFromBaseUnit(baseValue);

        return new Quantity<>(round(converted), targetUnit);
    }

    public Quantity<U> add(Quantity<U> other) {

        unit.validateOperationSupport("ADD");

        return add(other, this.unit);
    }

    public Quantity<U> add(Quantity<U> other, U targetUnit) {

        unit.validateOperationSupport("ADD");

        validateOperands(other);

        double resultBase =
                performBaseArithmetic(other, ArithmeticOperation.ADD);

        double result =
                targetUnit.convertFromBaseUnit(resultBase);

        return new Quantity<>(round(result), targetUnit);
    }

    public Quantity<U> subtract(Quantity<U> other) {

        unit.validateOperationSupport("SUBTRACT");

        return subtract(other, this.unit);
    }

    public Quantity<U> subtract(Quantity<U> other, U targetUnit) {

        unit.validateOperationSupport("SUBTRACT");

        validateOperands(other);

        double resultBase =
                performBaseArithmetic(other, ArithmeticOperation.SUBTRACT);

        double result =
                targetUnit.convertFromBaseUnit(resultBase);

        return new Quantity<>(round(result), targetUnit);
    }

    public double divide(Quantity<U> other) {

        unit.validateOperationSupport("DIVIDE");

        validateOperands(other);

        return performBaseArithmetic(other, ArithmeticOperation.DIVIDE);
    }

    private enum ArithmeticOperation {

        ADD((a, b) -> a + b),

        SUBTRACT((a, b) -> a - b),

        DIVIDE((a, b) -> {

            if (b == 0)
                throw new ArithmeticException("Division by zero");

            return a / b;
        });

        private final DoubleBinaryOperator operation;

        ArithmeticOperation(DoubleBinaryOperator op) {
            this.operation = op;
        }

        public double compute(double a, double b) {
            return operation.applyAsDouble(a, b);
        }
    }

    private void validateOperands(Quantity<U> other) {

        if (other == null)
            throw new IllegalArgumentException("Other quantity cannot be null");

        if (unit.getClass() != other.unit.getClass())
            throw new IllegalArgumentException("Different measurement categories");
    }

    private double performBaseArithmetic(
            Quantity<U> other,
            ArithmeticOperation operation
    ) {

        double base1 = unit.convertToBaseUnit(value);
        double base2 = other.unit.convertToBaseUnit(other.value);

        return operation.compute(base1, base2);
    }

    private double round(double value) {
        return Math.round(value * 100.0) / 100.0;
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj)
            return true;

        if (!(obj instanceof Quantity<?> other))
            return false;

        if (unit.getClass() != other.unit.getClass())
            return false;

        double base1 = unit.convertToBaseUnit(value);
        double base2 =
                ((IMeasurable) other.unit)
                        .convertToBaseUnit(other.value);

        return Math.abs(base1 - base2) < EPSILON;
    }

    @Override
    public int hashCode() {
        return Objects.hash(unit.convertToBaseUnit(value));
    }

    @Override
    public String toString() {
        return "Quantity(" + value + ", " + unit.getUnitName() + ")";
    }
    public double getValue() {
        return value;
    }

    public U getUnit() {
        return unit;
    }
}