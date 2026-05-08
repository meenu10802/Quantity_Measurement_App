package com.bridgelabz.service;

import com.bridgelabz.model.QuantityDTO;
import com.bridgelabz.model.QuantityMeasurementEntity;

public interface IQuantityMeasurementService {

    QuantityMeasurementEntity compare(QuantityDTO first, QuantityDTO second);

    QuantityMeasurementEntity convert(QuantityDTO quantity, String targetUnit);

    QuantityMeasurementEntity add(QuantityDTO first, QuantityDTO second);

    QuantityMeasurementEntity subtract(QuantityDTO first, QuantityDTO second);

    QuantityMeasurementEntity divide(QuantityDTO first, QuantityDTO second);
}