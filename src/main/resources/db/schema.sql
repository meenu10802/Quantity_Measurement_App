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
    );