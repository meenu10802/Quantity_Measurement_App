package com.bridgelabz.repository;

import com.bridgelabz.exception.DatabaseException;
import com.bridgelabz.model.QuantityDTO;
import com.bridgelabz.model.QuantityMeasurementEntity;
import com.bridgelabz.util.ConnectionPool;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class QuantityMeasurementDatabaseRepository implements IQuantityMeasurementRepository {

    private final ConnectionPool connectionPool;

    public QuantityMeasurementDatabaseRepository() {
        this.connectionPool = ConnectionPool.getInstance();
        initializeDatabase();
    }

    private void initializeDatabase() {
        String sql = """
                CREATE TABLE IF NOT EXISTS quantity_measurements (
                    id BIGINT AUTO_INCREMENT PRIMARY KEY,
                    operation_type VARCHAR(50),

                    first_value DOUBLE,
                    first_unit VARCHAR(50),
                    first_measurement_type VARCHAR(50),

                    second_value DOUBLE,
                    second_unit VARCHAR(50),
                    second_measurement_type VARCHAR(50),

                    result_value DOUBLE,
                    result_unit VARCHAR(50),
                    result_measurement_type VARCHAR(50),

                    comparison_result BOOLEAN,
                    error BOOLEAN,
                    error_message VARCHAR(255),

                    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
                )
                """;

        try (Connection connection = connectionPool.getConnection();
             Statement statement = connection.createStatement()) {

            statement.execute(sql);

        } catch (SQLException e) {
            throw new DatabaseException("Failed to initialize database table", e);
        }
    }

    @Override
    public void save(QuantityMeasurementEntity entity) {
        String sql = """
                INSERT INTO quantity_measurements (
                    operation_type,
                    first_value, first_unit, first_measurement_type,
                    second_value, second_unit, second_measurement_type,
                    result_value, result_unit, result_measurement_type,
                    comparison_result,
                    error,
                    error_message
                ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
                """;

        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, entity.getOperationType());

            setQuantity(statement, 2, entity.getFirstQuantity());
            setQuantity(statement, 5, entity.getSecondQuantity());
            setQuantity(statement, 8, entity.getResultQuantity());

            statement.setBoolean(11, entity.getResult());
            statement.setBoolean(12, entity.hasError());
            statement.setString(13, entity.getErrorMessage());

            statement.executeUpdate();

        } catch (SQLException e) {
            throw new DatabaseException("Failed to save measurement", e);
        }
    }

    @Override
    public List<QuantityMeasurementEntity> getAllMeasurements() {
        String sql = "SELECT * FROM quantity_measurements ORDER BY id";

        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            List<QuantityMeasurementEntity> measurements = new ArrayList<>();

            while (resultSet.next()) {
                measurements.add(mapRowToEntity(resultSet));
            }

            return measurements;

        } catch (SQLException e) {
            throw new DatabaseException("Failed to fetch measurements", e);
        }
    }

    @Override
    public List<QuantityMeasurementEntity> getMeasurementsByOperation(String operationType) {
        String sql = "SELECT * FROM quantity_measurements WHERE operation_type = ? ORDER BY id";

        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, operationType);

            try (ResultSet resultSet = statement.executeQuery()) {
                List<QuantityMeasurementEntity> measurements = new ArrayList<>();

                while (resultSet.next()) {
                    measurements.add(mapRowToEntity(resultSet));
                }

                return measurements;
            }

        } catch (SQLException e) {
            throw new DatabaseException("Failed to fetch measurements by operation", e);
        }
    }

    @Override
    public int getTotalCount() {
        String sql = "SELECT COUNT(*) FROM quantity_measurements";

        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            if (resultSet.next()) {
                return resultSet.getInt(1);
            }

            return 0;

        } catch (SQLException e) {
            throw new DatabaseException("Failed to count measurements", e);
        }
    }

    @Override
    public void deleteAllMeasurements() {
        String sql = "DELETE FROM quantity_measurements";

        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.executeUpdate();

        } catch (SQLException e) {
            throw new DatabaseException("Failed to delete measurements", e);
        }
    }

    private void setQuantity(PreparedStatement statement, int startIndex, QuantityDTO quantity)
            throws SQLException {

        if (quantity == null) {
            statement.setNull(startIndex, Types.DOUBLE);
            statement.setNull(startIndex + 1, Types.VARCHAR);
            statement.setNull(startIndex + 2, Types.VARCHAR);
            return;
        }

        statement.setDouble(startIndex, quantity.getValue());
        statement.setString(startIndex + 1, quantity.getUnit());
        statement.setString(startIndex + 2, quantity.getMeasurementType());
    }

    private QuantityMeasurementEntity mapRowToEntity(ResultSet resultSet) throws SQLException {
        String operationType = resultSet.getString("operation_type");
        boolean error = resultSet.getBoolean("error");

        if (error) {
            return new QuantityMeasurementEntity(
                    operationType,
                    resultSet.getString("error_message")
            );
        }

        QuantityDTO first = readQuantity(resultSet, "first");
        QuantityDTO second = readQuantity(resultSet, "second");
        QuantityDTO result = readQuantity(resultSet, "result");

        if ("COMPARE".equals(operationType)) {
            return new QuantityMeasurementEntity(
                    operationType,
                    first,
                    second,
                    resultSet.getBoolean("comparison_result")
            );
        }

        if ("CONVERT".equals(operationType)) {
            return new QuantityMeasurementEntity(
                    operationType,
                    first,
                    result
            );
        }

        return new QuantityMeasurementEntity(
                operationType,
                first,
                second,
                result
        );
    }

    private QuantityDTO readQuantity(ResultSet resultSet, String prefix) throws SQLException {
        String unit = resultSet.getString(prefix + "_unit");
        String measurementType = resultSet.getString(prefix + "_measurement_type");

        if (unit == null && measurementType == null) {
            return null;
        }

        return new QuantityDTO(
                resultSet.getDouble(prefix + "_value"),
                unit,
                measurementType
        );
    }
}