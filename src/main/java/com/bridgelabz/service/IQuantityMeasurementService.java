package com.bridgelabz.service;

import com.bridgelabz.dto.QuantityDTO;
import com.bridgelabz.model.QuantityMeasurementEntity;

import java.util.List;

public interface IQuantityMeasurementService {

    QuantityMeasurementEntity compare(QuantityDTO first, QuantityDTO second);

    QuantityMeasurementEntity convert(QuantityDTO quantity, String targetUnit);

    QuantityMeasurementEntity add(QuantityDTO first, QuantityDTO second);

    QuantityMeasurementEntity subtract(QuantityDTO first, QuantityDTO second);

    QuantityMeasurementEntity divide(QuantityDTO first, QuantityDTO second);

    List<QuantityMeasurementEntity> getHistoryByOperation(String operationType);

    List<QuantityMeasurementEntity> getHistoryByMeasurementType(String measurementType);

    List<QuantityMeasurementEntity> getErrorHistory();

    long getOperationCount(String operationType);
}