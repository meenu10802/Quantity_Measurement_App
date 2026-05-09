package com.bridgelabz.repository;

import com.bridgelabz.model.QuantityMeasurementEntity;

import java.util.List;

public interface IQuantityMeasurementRepository {

    void save(QuantityMeasurementEntity entity);

    List<QuantityMeasurementEntity> getAllMeasurements();

    default List<QuantityMeasurementEntity> getMeasurementsByOperation(String operationType) {
        throw new UnsupportedOperationException("Operation filtering not supported");
    }

    default int getTotalCount() {
        return getAllMeasurements().size();
    }

    default void deleteAllMeasurements() {
        throw new UnsupportedOperationException("Delete all not supported");
    }

    default void releaseResources() {
    }
}