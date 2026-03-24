package com.apps.quantitymeasurement.controller;

import com.apps.quantitymeasurement.dto.QuantityDTO;
import com.apps.quantitymeasurement.dto.TwoQuantityRequest;
import com.apps.quantitymeasurement.exception.QuantityMeasurementException;
import com.apps.quantitymeasurement.service.IQuantityMeasurementService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/quantity")
public class QuantityMeasurementController {

    private final IQuantityMeasurementService service;

    public QuantityMeasurementController(IQuantityMeasurementService service) {
        this.service = service;
    }

    // 1. Compare
    @PostMapping("/compare")
    public boolean compare(@RequestBody TwoQuantityRequest request) throws QuantityMeasurementException {
        return service.compare(request.getQ1(), request.getQ2());
    }

    // 2. Convert
    @PostMapping("/convert")
    public QuantityDTO convert(@RequestBody TwoQuantityRequest request) throws QuantityMeasurementException {
        return service.convert(request.getQ1(), request.getQ2());
    }

    // 3. Add
    @PostMapping("/add")
    public QuantityDTO add(@RequestBody TwoQuantityRequest request) throws QuantityMeasurementException {
        if(request.getTargetUnit() == null) {
            return service.add(request.getQ1(), request.getQ2());
        }
        return service.add(request.getQ1(), request.getQ2(), request.getTargetUnit());
    }

    // 4. Subtract
    @PostMapping("/subtract")
    public QuantityDTO subtract(@RequestBody TwoQuantityRequest request) throws QuantityMeasurementException {
        if(request.getTargetUnit() == null) {
            return service.subtract(request.getQ1(), request.getQ2());
        }
        return service.subtract(request.getQ1(), request.getQ2(), request.getTargetUnit());
    }

    // 5. Divide
    @PostMapping("/divide")
    public double divide(@RequestBody TwoQuantityRequest request) throws QuantityMeasurementException {
        return service.divide(request.getQ1(), request.getQ2());
    }
}