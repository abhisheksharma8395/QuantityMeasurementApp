package com.apps.quantitymeasurement.service;

import com.apps.quantitymeasurement.dto.QuantityDTO;
import com.apps.quantitymeasurement.entity.QuantityMeasurementEntity;
import com.apps.quantitymeasurement.exception.QuantityMeasurementException;
import com.apps.quantitymeasurement.model.QuantityModel;
import com.apps.quantitymeasurement.repository.IQuantityMeasurementRepository;
import com.apps.quantitymeasurement.unit.*;

public class QuantityMeasurementServiceImpl implements IQuantityMeasurementService{
    private IQuantityMeasurementRepository repository;

    public QuantityMeasurementServiceImpl(IQuantityMeasurementRepository repository){
        this.repository = repository;
    }

    private enum Operation{
        COMPARISON , CONVERSION , ARITHMETIC
    }

    public boolean compare(QuantityDTO thisQuantityDTO , QuantityDTO thatQuantityDTO) throws QuantityMeasurementException {
        QuantityModel<?> thisQuantityModel = getQuantityModel(thisQuantityDTO);
        QuantityModel<?> thatQuantityModel = getQuantityModel(thatQuantityDTO);
        return compare(thisQuantityModel,thatQuantityModel);
    }

    private boolean compare(QuantityModel<?> thisQuantity, QuantityModel<?> thatQuantity) throws QuantityMeasurementException{
        if (!thisQuantity.getUnit().getMeasurementType().equals(thatQuantity.getUnit().getMeasurementType())) {
            throw new QuantityMeasurementException("Cannot compare different measurement types");
        }
        double baseValue1 = thisQuantity.getValue() * thisQuantity.getUnit().getConversionFactor();
        double baseValue2 = thatQuantity.getValue() * thatQuantity.getUnit().getConversionFactor();
        return Math.abs(baseValue1 - baseValue2) < 0.0001;
    }

    @Override
    public QuantityDTO convert(QuantityDTO thisQuantityDTO, QuantityDTO thatQuantityDTO) throws QuantityMeasurementException {
        try {
            QuantityModel<?> source = getQuantityModel(thisQuantityDTO);
            QuantityModel<?> target = getQuantityModel(thatQuantityDTO);
            if (!source.getUnit().getMeasurementType().equals(target.getUnit().getMeasurementType())) {
                throw new QuantityMeasurementException("Cannot convert between different measurement types");
            }
            double baseValue = source.getValue() * source.getUnit().getConversionFactor();
            double convertedValue = baseValue / target.getUnit().getConversionFactor();
            QuantityModel<?> resultModel = new QuantityModel<>(convertedValue, target.getUnit());
            QuantityMeasurementEntity entity = new QuantityMeasurementEntity(source, null, "CONVERT", resultModel);
            repository.save(entity);
            return new QuantityDTO(convertedValue, target.getUnit().getUnitName(), target.getUnit().getMeasurementType());

        } catch (QuantityMeasurementException e) {
            throw new QuantityMeasurementException("Exception Occurred");
        }
    }

    @Override
    public QuantityDTO add(QuantityDTO thisQuantityDTO, QuantityDTO thatQuantityDTO) {
        return null;
    }

    @Override
    public QuantityDTO add(QuantityDTO thisQuantityDTO, QuantityDTO thatQuantityDTO, QuantityDTO targetUnitDTO) {
        return null;
    }

    @Override
    public QuantityDTO subtract(QuantityDTO thisQuantityDTO, QuantityDTO thatQuantityDTO) {
        return null;
    }

    @Override
    public QuantityDTO subtract(QuantityDTO thisQuantityDTO, QuantityDTO thatQuantityDTO, QuantityDTO targetUnitDTO) {
        return null;
    }

    @Override
    public double divide(QuantityDTO thisQuantityDTO, QuantityDTO thatQuantityDTO) {
        return 0;
    }

    private QuantityModel<?> getQuantityModel(QuantityDTO dto) throws QuantityMeasurementException {
        String unitName = dto.getUnit();
        String type = dto.getMeasurementType();
        IMeasurableUnit unit = null;
        switch (type) {
            case "LENGTH":
                unit = LengthUnit.valueOf(unitName);
                break;

            case "WEIGHT":
                unit = WeightUnit.valueOf(unitName);
                break;

            case "VOLUME":
                unit = VolumeUnit.valueOf(unitName);
                break;

            case "TEMPERATURE":
                unit = TemperatureUnit.valueOf(unitName);
                break;

            default:
                throw new QuantityMeasurementException("Invalid measurement type");
        }
        return new QuantityModel<>(dto.getValue(), unit);
    }
}
