package com.bridgelabz;

/**
 * Standalone LengthUnit enum responsible for
 * converting values to and from base unit (FEET).
 */
public enum LengthUnit {

    FEET(1.0),
    INCH(1.0 / 12.0),
    YARDS(3.0),
    CENTIMETER(1.0 / 30.48);

    private final double conversionFactorToFeet;

    LengthUnit(double conversionFactorToFeet) {
        this.conversionFactorToFeet = conversionFactorToFeet;
    }

    /**
     * Converts value from this unit to base unit (FEET)
     */
    public double convertToBaseUnit(double value) {
        return value * conversionFactorToFeet;
    }

    /**
     * Converts value from base unit (FEET) to this unit
     */
    public double convertFromBaseUnit(double baseValue) {
        return baseValue / conversionFactorToFeet;
    }

    public double getConversionFactor() {
        return conversionFactorToFeet;
    }
}