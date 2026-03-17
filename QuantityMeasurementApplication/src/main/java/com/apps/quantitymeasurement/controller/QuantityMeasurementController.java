package com.apps.quantitymeasurement.controller;

import com.apps.quantitymeasurement.dto.QuantityDTO;
import com.apps.quantitymeasurement.exception.QuantityMeasurementException;
import com.apps.quantitymeasurement.service.IQuantityMeasurementService;

import java.util.logging.Logger;

public class QuantityMeasurementController {
    private static final Logger logger = Logger.getLogger(QuantityMeasurementController.class.getName());
    private IQuantityMeasurementService quantityMeasurementService;

    public QuantityMeasurementController(IQuantityMeasurementService quantityMeasurementService){
        this.quantityMeasurementService = quantityMeasurementService;
        logger.info("QuantityMeasurementController initialized with service : "+quantityMeasurementService);
    }

    public boolean performComparison(QuantityDTO thisQuantityDTO , QuantityDTO thatQuantityDTO) throws QuantityMeasurementException {
        return quantityMeasurementService.compare(thisQuantityDTO,thatQuantityDTO);
    }

    public QuantityDTO performConversion(QuantityDTO thisQuantityDTO , QuantityDTO thatQuantityDTO) throws QuantityMeasurementException {
        return quantityMeasurementService.convert(thisQuantityDTO,thatQuantityDTO);
    }

    public QuantityDTO performAddition(QuantityDTO thisQuantityDTO , QuantityDTO thatQuantityDTO) throws QuantityMeasurementException {
        return quantityMeasurementService.add(thisQuantityDTO,thatQuantityDTO);
    }

    public QuantityDTO performAddition(QuantityDTO thisQuantityDTO , QuantityDTO thatQuantityDTO , QuantityDTO targetUnitDTO) throws QuantityMeasurementException {
        return quantityMeasurementService.add(thisQuantityDTO,thatQuantityDTO,targetUnitDTO);
    }

    public QuantityDTO performSubtraction(QuantityDTO thisQuantityDTO , QuantityDTO thatQuantityDTO) throws QuantityMeasurementException {
        return quantityMeasurementService.subtract(thisQuantityDTO,thatQuantityDTO);
    }

    public QuantityDTO performSubtraction(QuantityDTO thisQuantityDTO , QuantityDTO thatQuantityDTO , QuantityDTO targetUnitDTO) throws QuantityMeasurementException {
        return quantityMeasurementService.subtract(thisQuantityDTO,thatQuantityDTO,targetUnitDTO);
    }

    public double performDivision(QuantityDTO thisQuantityDTO , QuantityDTO thatQuantityDTO) throws QuantityMeasurementException {
        return quantityMeasurementService.divide(thisQuantityDTO,thatQuantityDTO);
    }
}
