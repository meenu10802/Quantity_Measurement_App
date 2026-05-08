package com.bridgelabz.controller;

import com.bridgelabz.model.QuantityDTO;
import com.bridgelabz.model.QuantityMeasurementEntity;
import com.bridgelabz.service.IQuantityMeasurementService;

public class QuantityMeasurementController {

    private final IQuantityMeasurementService service;

    public QuantityMeasurementController(IQuantityMeasurementService service) {
        if (service == null) {
            throw new IllegalArgumentException("Service cannot be null");
        }
        this.service = service;
    }

    public QuantityMeasurementEntity performCompare(QuantityDTO first, QuantityDTO second) {
        return service.compare(first, second);
    }

    public QuantityMeasurementEntity performConvert(QuantityDTO quantity, String targetUnit) {
        return service.convert(quantity, targetUnit);
    }

    public QuantityMeasurementEntity performAdd(QuantityDTO first, QuantityDTO second) {
        return service.add(first, second);
    }

    public QuantityMeasurementEntity performSubtract(QuantityDTO first, QuantityDTO second) {
        return service.subtract(first, second);
    }

    public QuantityMeasurementEntity performDivide(QuantityDTO first, QuantityDTO second) {
        return service.divide(first, second);
    }

    public void demonstrateOperations() {
        QuantityDTO celsius =
                new QuantityDTO(0.0, "CELSIUS", "TEMPERATURE");

        QuantityDTO fahrenheit =
                new QuantityDTO(32.0, "FAHRENHEIT", "TEMPERATURE");

        displayResult(performCompare(celsius, fahrenheit));

        QuantityDTO hundredCelsius =
                new QuantityDTO(100.0, "CELSIUS", "TEMPERATURE");

        displayResult(performConvert(hundredCelsius, "FAHRENHEIT"));

        QuantityDTO feet =
                new QuantityDTO(10.0, "FEET", "LENGTH");

        QuantityDTO inch =
                new QuantityDTO(12.0, "INCH", "LENGTH");

        displayResult(performAdd(feet, inch));

        displayResult(performSubtract(feet, inch));

        displayResult(performDivide(feet, inch));

        displayResult(performAdd(hundredCelsius, celsius));
    }

    public void displayResult(QuantityMeasurementEntity entity) {
        if (entity.hasError()) {
            System.out.println("Error in " + entity.getOperationType() + ": "
                    + entity.getErrorMessage());
        } else {
            System.out.println(entity);
        }
    }
}