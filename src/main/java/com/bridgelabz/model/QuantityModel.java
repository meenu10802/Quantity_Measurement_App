package com.bridgelabz.model;

import com.bridgelabz.unit.IMeasurable;

public class QuantityModel<U extends IMeasurable> {

    private double value;
    private U unit;

    public QuantityModel() {
    }

    public QuantityModel(double value, U unit) {
        this.value = value;
        this.unit = unit;
    }

    public double getValue() {
        return value;
    }

    public U getUnit() {
        return unit;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public void setUnit(U unit) {
        this.unit = unit;
    }
}