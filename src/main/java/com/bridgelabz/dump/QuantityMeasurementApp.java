package com.bridgelabz.dump;

import com.bridgelabz.controller.QuantityMeasurementController;
import com.bridgelabz.repository.IQuantityMeasurementRepository;
import com.bridgelabz.repository.QuantityMeasurementDatabaseRepository;
import com.bridgelabz.service.IQuantityMeasurementService;
import com.bridgelabz.service.QuantityMeasurementServiceImpl;

public class QuantityMeasurementApp {

    public static void main(String[] args) {

        IQuantityMeasurementRepository repository =
                new QuantityMeasurementDatabaseRepository();

        repository.deleteAllMeasurements();

        IQuantityMeasurementService service =
                new QuantityMeasurementServiceImpl(repository);

        QuantityMeasurementController controller =
                new QuantityMeasurementController(service);

        controller.demonstrateOperations();

        System.out.println("Total measurements saved in database: "
                + repository.getTotalCount());

        System.out.println("All database records:");
        repository.getAllMeasurements()
                .forEach(System.out::println);
    }
}