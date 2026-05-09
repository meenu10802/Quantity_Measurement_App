package com.bridgelabz.controller;

import com.bridgelabz.dto.QuantityInputDTO;
import com.bridgelabz.model.QuantityMeasurementEntity;
import com.bridgelabz.service.IQuantityMeasurementService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/quantities")
@Tag(name = "Quantity Measurements", description = "REST API for quantity measurement operations")
public class QuantityMeasurementController {

    private final IQuantityMeasurementService service;

    public QuantityMeasurementController(IQuantityMeasurementService service) {
        this.service = service;
    }

    @PostMapping("/compare")
    @Operation(summary = "Compare two quantities")
    public QuantityMeasurementEntity compare(@RequestBody QuantityInputDTO input) {
        return service.compare(input.getFirstQuantity(), input.getSecondQuantity());
    }

    @PostMapping("/convert")
    @Operation(summary = "Convert one quantity to target unit")
    public QuantityMeasurementEntity convert(@RequestBody QuantityInputDTO input) {
        return service.convert(input.getFirstQuantity(), input.getTargetUnit());
    }

    @PostMapping("/add")
    @Operation(summary = "Add two quantities")
    public QuantityMeasurementEntity add(@RequestBody QuantityInputDTO input) {
        return service.add(input.getFirstQuantity(), input.getSecondQuantity());
    }

    @PostMapping("/subtract")
    @Operation(summary = "Subtract two quantities")
    public QuantityMeasurementEntity subtract(@RequestBody QuantityInputDTO input) {
        return service.subtract(input.getFirstQuantity(), input.getSecondQuantity());
    }

    @PostMapping("/divide")
    @Operation(summary = "Divide two quantities")
    public QuantityMeasurementEntity divide(@RequestBody QuantityInputDTO input) {
        return service.divide(input.getFirstQuantity(), input.getSecondQuantity());
    }

    @GetMapping("/history/operation/{operationType}")
    @Operation(summary = "Get history by operation type")
    public List<QuantityMeasurementEntity> getHistoryByOperation(
            @PathVariable String operationType) {
        return service.getHistoryByOperation(operationType);
    }

    @GetMapping("/history/type/{measurementType}")
    @Operation(summary = "Get history by measurement type")
    public List<QuantityMeasurementEntity> getHistoryByMeasurementType(
            @PathVariable String measurementType) {
        return service.getHistoryByMeasurementType(measurementType);
    }

    @GetMapping("/history/errors")
    @Operation(summary = "Get all failed/error operations")
    public List<QuantityMeasurementEntity> getErrorHistory() {
        return service.getErrorHistory();
    }

    @GetMapping("/count/{operationType}")
    @Operation(summary = "Get operation count")
    public long getOperationCount(@PathVariable String operationType) {
        return service.getOperationCount(operationType);
    }
}