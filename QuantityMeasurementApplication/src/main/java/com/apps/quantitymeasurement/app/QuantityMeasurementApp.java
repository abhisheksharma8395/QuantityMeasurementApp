package com.apps.quantitymeasurement.app;

import com.apps.quantitymeasurement.controller.QuantityMeasurementController;
import com.apps.quantitymeasurement.dto.QuantityDTO;
import com.apps.quantitymeasurement.repository.IQuantityMeasurementRepository;
import com.apps.quantitymeasurement.repository.QuantityMeasurementCacheRepository;
import com.apps.quantitymeasurement.service.QuantityMeasurementServiceImpl;

public class QuantityMeasurementApp {

    private static QuantityMeasurementApp instance;
    public QuantityMeasurementController controller;
    public IQuantityMeasurementRepository repository;

    private QuantityMeasurementApp(){
        this.repository = QuantityMeasurementCacheRepository.getInstance();
        QuantityMeasurementServiceImpl service = new QuantityMeasurementServiceImpl(this.repository);
        this.controller = new QuantityMeasurementController(service);
    }

    public static QuantityMeasurementApp getInstance(){
        if(instance == null) instance = new QuantityMeasurementApp();
        return instance;
    }

    public static void main(String[] args) {
        QuantityMeasurementApp app = QuantityMeasurementApp.getInstance();

        try {

            // Example 1: Comparison (1 foot == 12 inches)
            QuantityDTO q1 = new QuantityDTO(1, "FEET", "LENGTH");
            QuantityDTO q2 = new QuantityDTO(12, "INCHES", "LENGTH");
            boolean result = app.controller.performComparison(q1, q2);
            System.out.println("Comparison Result: " + result);


            // Example 2: Conversion (1 gallon → litre)
            QuantityDTO source = new QuantityDTO(1, "GALLON", "VOLUME");
            QuantityDTO target = new QuantityDTO(0, "LITRE", "VOLUME");
            QuantityDTO converted = app.controller.performConversion(source, target);
            System.out.println("Converted Value: " + converted.getValue() + " " + converted.getUnit());


            // Example 3: Addition
            QuantityDTO a = new QuantityDTO(1, "FEET", "LENGTH");
            QuantityDTO b = new QuantityDTO(12, "INCHES", "LENGTH");
            QuantityDTO sum = app.controller.performAddition(a, b);
            System.out.println("Addition Result: " + sum.getValue() + " " + sum.getUnit());


            // Example 4: Division
            QuantityDTO d1 = new QuantityDTO(10, "FEET", "LENGTH");
            QuantityDTO d2 = new QuantityDTO(5, "FEET", "LENGTH");
            double divResult = app.controller.performDivision(d1, d2);
            System.out.println("Division Result: " + divResult);

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}

