package com.bridgelabz.repository;

import com.bridgelabz.model.QuantityDTO;
import com.bridgelabz.model.QuantityMeasurementEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementDatabaseRepositoryTest {

    private IQuantityMeasurementRepository repository;

    @BeforeEach
    void setUp() {
        repository = new QuantityMeasurementDatabaseRepository();
        repository.deleteAllMeasurements();
    }

    @Test
    void givenMeasurement_WhenSaved_ShouldIncreaseDatabaseCount() {

        QuantityDTO first =
                new QuantityDTO(10.0, "FEET", "LENGTH");

        QuantityDTO second =
                new QuantityDTO(12.0, "INCH", "LENGTH");

        QuantityDTO result =
                new QuantityDTO(11.0, "FEET", "LENGTH");

        QuantityMeasurementEntity entity =
                new QuantityMeasurementEntity("ADD", first, second, result);

        repository.save(entity);

        assertEquals(1, repository.getTotalCount());
    }

    @Test
    void givenMeasurements_WhenFetched_ShouldReturnAllRows() {

        QuantityDTO first =
                new QuantityDTO(10.0, "FEET", "LENGTH");

        QuantityDTO second =
                new QuantityDTO(12.0, "INCH", "LENGTH");

        QuantityDTO result =
                new QuantityDTO(11.0, "FEET", "LENGTH");

        repository.save(
                new QuantityMeasurementEntity("ADD", first, second, result)
        );

        repository.save(
                new QuantityMeasurementEntity("SUBTRACT", first, second, result)
        );

        List<QuantityMeasurementEntity> measurements =
                repository.getAllMeasurements();

        assertEquals(2, measurements.size());
    }

    @Test
    void givenOperationType_WhenFiltered_ShouldReturnMatchingRows() {

        QuantityDTO first =
                new QuantityDTO(10.0, "FEET", "LENGTH");

        QuantityDTO second =
                new QuantityDTO(12.0, "INCH", "LENGTH");

        QuantityDTO result =
                new QuantityDTO(11.0, "FEET", "LENGTH");

        repository.save(
                new QuantityMeasurementEntity("ADD", first, second, result)
        );

        repository.save(
                new QuantityMeasurementEntity("SUBTRACT", first, second, result)
        );

        List<QuantityMeasurementEntity> addMeasurements =
                repository.getMeasurementsByOperation("ADD");

        assertEquals(1, addMeasurements.size());
        assertEquals("ADD", addMeasurements.get(0).getOperationType());
    }

    @Test
    void givenDeleteAll_WhenCalled_ShouldClearDatabase() {

        QuantityDTO first =
                new QuantityDTO(10.0, "FEET", "LENGTH");

        QuantityDTO second =
                new QuantityDTO(12.0, "INCH", "LENGTH");

        QuantityDTO result =
                new QuantityDTO(11.0, "FEET", "LENGTH");

        repository.save(
                new QuantityMeasurementEntity("ADD", first, second, result)
        );

        assertEquals(1, repository.getTotalCount());

        repository.deleteAllMeasurements();

        assertEquals(0, repository.getTotalCount());
    }

    @Test
    void givenErrorMeasurement_WhenSaved_ShouldPersistErrorRow() {

        QuantityMeasurementEntity errorEntity =
                new QuantityMeasurementEntity(
                        "ADD",
                        "Temperature does not support arithmetic operation: ADD"
                );

        repository.save(errorEntity);

        List<QuantityMeasurementEntity> measurements =
                repository.getAllMeasurements();

        assertEquals(1, measurements.size());
        assertTrue(measurements.get(0).hasError());
        assertEquals(
                "Temperature does not support arithmetic operation: ADD",
                measurements.get(0).getErrorMessage()
        );
    }

    @Test
    void givenCompareMeasurement_WhenSaved_ShouldPersistBooleanResult() {

        QuantityDTO first =
                new QuantityDTO(0.0, "CELSIUS", "TEMPERATURE");

        QuantityDTO second =
                new QuantityDTO(32.0, "FAHRENHEIT", "TEMPERATURE");

        QuantityMeasurementEntity entity =
                new QuantityMeasurementEntity("COMPARE", first, second, true);

        repository.save(entity);

        List<QuantityMeasurementEntity> measurements =
                repository.getAllMeasurements();

        assertEquals(1, measurements.size());
        assertTrue(measurements.get(0).getResult());
    }
}