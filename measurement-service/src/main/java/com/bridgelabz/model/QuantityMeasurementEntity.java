package com.bridgelabz.model;

import com.bridgelabz.dto.QuantityDTO;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "quantity_measurements")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QuantityMeasurementEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String operationType;

    private Double firstValue;
    private String firstUnit;
    private String firstMeasurementType;

    private Double secondValue;
    private String secondUnit;
    private String secondMeasurementType;

    private Double resultValue;
    private String resultUnit;
    private String resultMeasurementType;

    private Boolean comparisonResult;

    private Boolean error;

    private String errorMessage;

    private LocalDateTime createdAt;

    public QuantityMeasurementEntity(String operationType,
                                     QuantityDTO firstQuantity,
                                     QuantityDTO resultQuantity) {
        this.operationType = operationType;
        setFirstQuantity(firstQuantity);
        setResultQuantity(resultQuantity);
        this.error = false;
    }

    public QuantityMeasurementEntity(String operationType,
                                     QuantityDTO firstQuantity,
                                     QuantityDTO secondQuantity,
                                     QuantityDTO resultQuantity) {
        this.operationType = operationType;
        setFirstQuantity(firstQuantity);
        setSecondQuantity(secondQuantity);
        setResultQuantity(resultQuantity);
        this.error = false;
    }

    public QuantityMeasurementEntity(String operationType,
                                     QuantityDTO firstQuantity,
                                     QuantityDTO secondQuantity,
                                     boolean comparisonResult) {
        this.operationType = operationType;
        setFirstQuantity(firstQuantity);
        setSecondQuantity(secondQuantity);
        this.comparisonResult = comparisonResult;
        this.error = false;
    }

    public QuantityMeasurementEntity(String operationType,
                                     String errorMessage) {
        this.operationType = operationType;
        this.errorMessage = errorMessage;
        this.error = true;
    }

    @PrePersist
    public void onCreate() {
        this.createdAt = LocalDateTime.now();

        if (this.error == null) {
            this.error = false;
        }
    }

    public QuantityDTO getFirstQuantity() {
        if (firstUnit == null && firstMeasurementType == null) {
            return null;
        }
        return new QuantityDTO(firstValue, firstUnit, firstMeasurementType);
    }

    public QuantityDTO getSecondQuantity() {
        if (secondUnit == null && secondMeasurementType == null) {
            return null;
        }
        return new QuantityDTO(secondValue, secondUnit, secondMeasurementType);
    }

    public QuantityDTO getResultQuantity() {
        if (resultUnit == null && resultMeasurementType == null) {
            return null;
        }
        return new QuantityDTO(resultValue, resultUnit, resultMeasurementType);
    }

    public boolean getResult() {
        return Boolean.TRUE.equals(comparisonResult);
    }

    public boolean hasError() {
        return Boolean.TRUE.equals(error);
    }

    private void setFirstQuantity(QuantityDTO quantity) {
        if (quantity != null) {
            this.firstValue = quantity.getValue();
            this.firstUnit = quantity.getUnit();
            this.firstMeasurementType = quantity.getMeasurementType();
        }
    }

    private void setSecondQuantity(QuantityDTO quantity) {
        if (quantity != null) {
            this.secondValue = quantity.getValue();
            this.secondUnit = quantity.getUnit();
            this.secondMeasurementType = quantity.getMeasurementType();
        }
    }

    private void setResultQuantity(QuantityDTO quantity) {
        if (quantity != null) {
            this.resultValue = quantity.getValue();
            this.resultUnit = quantity.getUnit();
            this.resultMeasurementType = quantity.getMeasurementType();
        }
    }

    @Override
    public String toString() {
        if (hasError()) {
            return "Operation: " + operationType + ", Error: " + errorMessage;
        }

        if ("COMPARE".equals(operationType)) {
            return "Operation: " + operationType +
                    ", First: " + getFirstQuantity() +
                    ", Second: " + getSecondQuantity() +
                    ", Result: " + comparisonResult;
        }

        if ("CONVERT".equals(operationType)) {
            return "Operation: " + operationType +
                    ", Input: " + getFirstQuantity() +
                    ", Result: " + getResultQuantity();
        }

        return "Operation: " + operationType +
                ", First: " + getFirstQuantity() +
                ", Second: " + getSecondQuantity() +
                ", Result Quantity: " + getResultQuantity();
    }
}