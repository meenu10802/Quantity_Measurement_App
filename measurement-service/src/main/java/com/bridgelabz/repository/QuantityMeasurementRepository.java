package com.bridgelabz.repository;

import com.bridgelabz.model.QuantityMeasurementEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuantityMeasurementRepository
        extends JpaRepository<QuantityMeasurementEntity, Long> {

    List<QuantityMeasurementEntity> findByOperationType(String operationType);

    List<QuantityMeasurementEntity> findByFirstMeasurementType(String measurementType);

    List<QuantityMeasurementEntity> findByErrorTrue();

    long countByOperationTypeAndErrorFalse(String operationType);
}