package com.bridgelabz.service;

import com.bridgelabz.*;
import com.bridgelabz.exception.QuantityMeasurementException;
import com.bridgelabz.model.QuantityDTO;
import com.bridgelabz.model.QuantityMeasurementEntity;
import com.bridgelabz.repository.IQuantityMeasurementRepository;
import com.bridgelabz.unit.IMeasurable;
import com.bridgelabz.unit.LengthUnit;
import com.bridgelabz.unit.TemperatureUnit;
import com.bridgelabz.unit.VolumeUnit;

public class QuantityMeasurementServiceImpl implements IQuantityMeasurementService {

    private final IQuantityMeasurementRepository repository;

    public QuantityMeasurementServiceImpl(IQuantityMeasurementRepository repository) {
        if (repository == null) {
            throw new QuantityMeasurementException("Repository cannot be null");
        }
        this.repository = repository;
    }

    @Override
    public QuantityMeasurementEntity compare(QuantityDTO first, QuantityDTO second) {
        try {
            Quantity quantity1 = toQuantity(first);
            Quantity quantity2 = toQuantity(second);

            boolean result = quantity1.equals(quantity2);

            QuantityMeasurementEntity entity =
                    new QuantityMeasurementEntity("COMPARE", first, second, result);

            repository.save(entity);
            return entity;

        } catch (Exception e) {
            return saveError("COMPARE", e);
        }
    }

    @Override
    public QuantityMeasurementEntity convert(QuantityDTO quantity, String targetUnit) {
        try {
            Quantity source = toQuantity(quantity);
            IMeasurable target = resolveUnit(quantity.getMeasurementType(), targetUnit);

            Quantity converted = source.convertTo(target);

            QuantityDTO resultDTO = new QuantityDTO(
                    converted.getValue(),
                    converted.getUnit().getUnitName(),
                    quantity.getMeasurementType()
            );

            QuantityMeasurementEntity entity =
                    new QuantityMeasurementEntity("CONVERT", quantity, resultDTO);

            repository.save(entity);
            return entity;

        } catch (Exception e) {
            return saveError("CONVERT", e);
        }
    }

    @Override
    public QuantityMeasurementEntity add(QuantityDTO first, QuantityDTO second) {
        try {
            Quantity quantity1 = toQuantity(first);
            Quantity quantity2 = toQuantity(second);

            Quantity result = quantity1.add(quantity2);

            QuantityDTO resultDTO = new QuantityDTO(
                    result.getValue(),
                    result.getUnit().getUnitName(),
                    first.getMeasurementType()
            );

            QuantityMeasurementEntity entity =
                    new QuantityMeasurementEntity("ADD", first, second, resultDTO);

            repository.save(entity);
            return entity;

        } catch (Exception e) {
            return saveError("ADD", e);
        }
    }

    @Override
    public QuantityMeasurementEntity subtract(QuantityDTO first, QuantityDTO second) {
        try {
            Quantity quantity1 = toQuantity(first);
            Quantity quantity2 = toQuantity(second);

            Quantity result = quantity1.subtract(quantity2);

            QuantityDTO resultDTO = new QuantityDTO(
                    result.getValue(),
                    result.getUnit().getUnitName(),
                    first.getMeasurementType()
            );

            QuantityMeasurementEntity entity =
                    new QuantityMeasurementEntity("SUBTRACT", first, second, resultDTO);

            repository.save(entity);
            return entity;

        } catch (Exception e) {
            return saveError("SUBTRACT", e);
        }
    }

    @Override
    public QuantityMeasurementEntity divide(QuantityDTO first, QuantityDTO second) {
        try {
            Quantity quantity1 = toQuantity(first);
            Quantity quantity2 = toQuantity(second);

            double result = quantity1.divide(quantity2);

            QuantityDTO resultDTO = new QuantityDTO(
                    result,
                    "SCALAR",
                    "NUMBER"
            );

            QuantityMeasurementEntity entity =
                    new QuantityMeasurementEntity("DIVIDE", first, second, resultDTO);

            repository.save(entity);
            return entity;

        } catch (Exception e) {
            return saveError("DIVIDE", e);
        }
    }

    private Quantity toQuantity(QuantityDTO dto) {
        if (dto == null) {
            throw new QuantityMeasurementException("QuantityDTO cannot be null");
        }

        IMeasurable unit = resolveUnit(dto.getMeasurementType(), dto.getUnit());

        return new Quantity(dto.getValue(), unit);
    }

    private IMeasurable resolveUnit(String measurementType, String unitName) {
        if (measurementType == null || unitName == null) {
            throw new QuantityMeasurementException("Measurement type and unit cannot be null");
        }

        String type = measurementType.trim().toUpperCase();
        String unit = unitName.trim().toUpperCase();

        return switch (type) {
            case "LENGTH" -> LengthUnit.valueOf(unit);
            case "WEIGHT" -> WeightUnit.valueOf(unit);
            case "VOLUME" -> VolumeUnit.valueOf(unit);
            case "TEMPERATURE" -> TemperatureUnit.valueOf(unit);
            default -> throw new QuantityMeasurementException("Invalid measurement type: " + measurementType);
        };
    }

    private QuantityMeasurementEntity saveError(String operationType, Exception e) {
        QuantityMeasurementEntity entity =
                new QuantityMeasurementEntity(operationType, e.getMessage());

        repository.save(entity);
        return entity;
    }
}