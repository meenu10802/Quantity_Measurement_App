package com.bridgelabz.model;

import java.io.Serializable;

public class QuantityMeasurementEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private QuantityDTO firstQuantity;
    private QuantityDTO secondQuantity;
    private QuantityDTO resultQuantity;
    private String operationType;
    private boolean result;
    private boolean error;
    private String errorMessage;

    public QuantityMeasurementEntity() {
    }

    public QuantityMeasurementEntity(String operationType,
                                     QuantityDTO firstQuantity,
                                     QuantityDTO resultQuantity) {
        this.operationType = operationType;
        this.firstQuantity = firstQuantity;
        this.resultQuantity = resultQuantity;
        this.error = false;
    }

    public QuantityMeasurementEntity(String operationType,
                                     QuantityDTO firstQuantity,
                                     QuantityDTO secondQuantity,
                                     QuantityDTO resultQuantity) {
        this.operationType = operationType;
        this.firstQuantity = firstQuantity;
        this.secondQuantity = secondQuantity;
        this.resultQuantity = resultQuantity;
        this.error = false;
    }

    public QuantityMeasurementEntity(String operationType,
                                     QuantityDTO firstQuantity,
                                     QuantityDTO secondQuantity,
                                     boolean result) {
        this.operationType = operationType;
        this.firstQuantity = firstQuantity;
        this.secondQuantity = secondQuantity;
        this.result = result;
        this.error = false;
    }

    public QuantityMeasurementEntity(String operationType,
                                     String errorMessage) {
        this.operationType = operationType;
        this.errorMessage = errorMessage;
        this.error = true;
    }

    public QuantityDTO getFirstQuantity() {
        return firstQuantity;
    }

    public QuantityDTO getSecondQuantity() {
        return secondQuantity;
    }

    public QuantityDTO getResultQuantity() {
        return resultQuantity;
    }

    public String getOperationType() {
        return operationType;
    }

    public boolean getResult() {
        return result;
    }

    public boolean hasError() {
        return error;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    @Override
    public String toString() {
        if (error) {
            return "Operation: " + operationType + ", Error: " + errorMessage;
        }

        if ("COMPARE".equals(operationType)) {
            return "Operation: " + operationType +
                    ", First: " + firstQuantity +
                    ", Second: " + secondQuantity +
                    ", Result: " + result;
        }

        if ("CONVERT".equals(operationType)) {
            return "Operation: " + operationType +
                    ", Input: " + firstQuantity +
                    ", Result: " + resultQuantity;
        }

        return "Operation: " + operationType +
                ", First: " + firstQuantity +
                ", Second: " + secondQuantity +
                ", Result Quantity: " + resultQuantity;
    }
}