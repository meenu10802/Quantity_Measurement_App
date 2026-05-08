package com.bridgelabz;

import com.bridgelabz.controller.QuantityMeasurementController;
import com.bridgelabz.repository.IQuantityMeasurementRepository;
import com.bridgelabz.repository.QuantityMeasurementCacheRepository;
import com.bridgelabz.service.IQuantityMeasurementService;
import com.bridgelabz.service.QuantityMeasurementServiceImpl;

public class QuantityMeasurementApp {

    public static void main(String[] args) {

        IQuantityMeasurementRepository repository =
                QuantityMeasurementCacheRepository.getInstance();

        IQuantityMeasurementService service =
                new QuantityMeasurementServiceImpl(repository);

        QuantityMeasurementController controller =
                new QuantityMeasurementController(service);

        controller.demonstrateOperations();
    }
}