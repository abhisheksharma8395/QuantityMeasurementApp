package com.apps.quantitymeasurement.service;

import com.apps.quantitymeasurement.dto.QuantityDTO;
import com.apps.quantitymeasurement.entity.QuantityMeasurementEntity;
import com.apps.quantitymeasurement.exception.QuantityMeasurementException;
import com.apps.quantitymeasurement.model.QuantityModel;
import com.apps.quantitymeasurement.repository.QuantityMeasurementRepository;
import com.apps.quantitymeasurement.unit.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Service
public class QuantityMeasurementServiceImpl implements IQuantityMeasurementService{
    private final QuantityMeasurementRepository repository;

    public QuantityMeasurementServiceImpl(QuantityMeasurementRepository repository){
        this.repository = repository;
    }

    private enum Operation{
        COMPARISON , CONVERSION , ARITHMETIC
    }

    public boolean compare(QuantityDTO thisQuantityDTO , QuantityDTO thatQuantityDTO) throws QuantityMeasurementException {
        QuantityModel<?> thisQuantityModel = getQuantityModel(thisQuantityDTO);
        QuantityModel<?> thatQuantityModel = getQuantityModel(thatQuantityDTO);
        boolean comparisonResult = compare(thisQuantityModel,thatQuantityModel);
        String resultString = comparisonResult ? "True" : "False";
        String userName = getUserName();
        QuantityMeasurementEntity entity = new QuantityMeasurementEntity(thisQuantityModel,thatQuantityModel,"COMPARE",resultString,userName);
        repository.save(entity);
        return comparisonResult;
    }

    private boolean compare(QuantityModel<?> thisQuantity, QuantityModel<?> thatQuantity) throws QuantityMeasurementException{
        if (!thisQuantity.getUnit().getMeasurementType().equals(thatQuantity.getUnit().getMeasurementType())) {
            throw new QuantityMeasurementException("Cannot compare different measurement types");
        }
        if(thisQuantity.getUnit().getClass().equals(TemperatureUnit.class)){
            return compareTo(thisQuantity,thatQuantity);
        }
        else {
            double baseValue1 = thisQuantity.getValue() * thisQuantity.getUnit().getConversionFactor();
            double baseValue2 = thatQuantity.getValue() * thatQuantity.getUnit().getConversionFactor();
            return Math.abs(baseValue1 - baseValue2) <= 0.1;
        }
    }

    private boolean compareTo(QuantityModel<?> thisQuantity , QuantityModel<?> thatQuantity){
        TemperatureUnit thisUnit = (TemperatureUnit) thisQuantity.getUnit();
        TemperatureUnit thatUnit = (TemperatureUnit) thatQuantity.getUnit();
        double newValue = thisUnit.convertTo(thisQuantity.getValue(),thatUnit);
        return Math.abs(newValue - (thatQuantity.getValue())) <= 0.1;
    }

    @Override
    public QuantityDTO convert(QuantityDTO thisQuantityDTO, QuantityDTO thatQuantityDTO) throws QuantityMeasurementException {
        try {
            QuantityModel<?> source = getQuantityModel(thisQuantityDTO);
            QuantityModel<?> target = getQuantityModel(thatQuantityDTO);
            if (!(source.getUnit().getMeasurementType().equals(target.getUnit().getMeasurementType()))) {
                throw new QuantityMeasurementException("Cannot convert between different measurement types");
            }
            if(source.getUnit().getClass().equals(TemperatureUnit.class)){
                return convertTo(source,target);
            }
            else {
                double baseValue = source.getValue() * (source.getUnit().getConversionFactor());
                double convertedValue = baseValue / (target.getUnit().getConversionFactor());
                QuantityModel<?> resultModel = new QuantityModel<>(convertedValue, target.getUnit());
                String userName = getUserName();
                QuantityMeasurementEntity entity = new QuantityMeasurementEntity(source, "CONVERT", resultModel,userName);
                repository.save(entity);
                return new QuantityDTO(convertedValue, target.getUnit().getUnitName(), target.getUnit().getMeasurementType());
            }

        } catch (QuantityMeasurementException e) {
            throw new QuantityMeasurementException("Exception Occurred");
        }
    }

    public QuantityDTO convertTo(QuantityModel<?> thisQuantityModel , QuantityModel<?> thatQuantityModel){
        TemperatureUnit thisUnit = (TemperatureUnit) thisQuantityModel.getUnit();
        TemperatureUnit thatUnit = (TemperatureUnit) thatQuantityModel.getUnit();
        double newValue = thisUnit.convertTo(thisQuantityModel.getValue(),thatUnit);
        QuantityModel<TemperatureUnit> resultModel = new QuantityModel<>(newValue,thatUnit);
        String userName = getUserName();
        QuantityMeasurementEntity entity = new QuantityMeasurementEntity(thisQuantityModel,"CONVERT",resultModel,userName);
        repository.save(entity);
        return new QuantityDTO(newValue,thatUnit.getUnitName(),thatUnit.getMeasurementType());
    }

    @Override
    public QuantityDTO add(QuantityDTO thisQuantityDTO, QuantityDTO thatQuantityDTO) throws QuantityMeasurementException {
        QuantityModel<?> q1 = getQuantityModel(thisQuantityDTO);
        QuantityModel<?> q2 = getQuantityModel(thatQuantityDTO);
        if (!q1.getUnit().getMeasurementType().equals(q2.getUnit().getMeasurementType())) {
            throw new QuantityMeasurementException("Cannot add different measurement types");
        }

        if(q1.getUnit().getClass().equals(TemperatureUnit.class)){
            return add(q1,q2);
        }
        else {
            double baseValue1 = q1.getValue() * q1.getUnit().getConversionFactor();
            double baseValue2 = q2.getValue() * q2.getUnit().getConversionFactor();
            double resultBase = baseValue1 + baseValue2;
            double resultValue = resultBase / q1.getUnit().getConversionFactor();
            QuantityModel<?> resultModel = new QuantityModel<>(resultValue, q1.getUnit());
            String userName = getUserName();
            QuantityMeasurementEntity entity = new QuantityMeasurementEntity(q1, q2, "ADD", resultModel,userName);
            repository.save(entity);
            return new QuantityDTO(resultValue, q1.getUnit().getUnitName(), q1.getUnit().getMeasurementType());
        }
    }

    public QuantityDTO add(QuantityModel<?> thisQuantity, QuantityModel<?> thatQuantity) throws QuantityMeasurementException {
        TemperatureUnit thisUnit = (TemperatureUnit) thisQuantity.getUnit();
        TemperatureUnit thatUnit = (TemperatureUnit) thatQuantity.getUnit();
        double newThatQuantityValue = thisUnit.convertTo(thatQuantity.getValue(),thisUnit);
        double thisQuantityValue = thisQuantity.getValue();
        double newValue = newThatQuantityValue + thisQuantityValue;
        QuantityModel<TemperatureUnit> resultModel = new QuantityModel<>(newValue,thisUnit);
        String userName = getUserName();
        QuantityMeasurementEntity entity = new QuantityMeasurementEntity(thisQuantity, thatQuantity, "ADD", resultModel,userName);
        repository.save(entity);
        return new QuantityDTO(newValue, thisQuantity.getUnit().getUnitName(), thisQuantity.getUnit().getMeasurementType());
    }

    @Override
    public QuantityDTO add(QuantityDTO thisQuantityDTO, QuantityDTO thatQuantityDTO, QuantityDTO targetUnitDTO) throws QuantityMeasurementException {
        QuantityModel<?> q1 = getQuantityModel(thisQuantityDTO);
        QuantityModel<?> q2 = getQuantityModel(thatQuantityDTO);
        QuantityModel<?> target = getQuantityModel(targetUnitDTO);

        if (!q1.getUnit().getMeasurementType().equals(q2.getUnit().getMeasurementType())) {
            throw new QuantityMeasurementException("Cannot add different measurement types");
        }

        if(q1.getUnit().getClass().equals(TemperatureUnit.class)){
            return add(q1,q2,target);
        }

        else {
            double base1 = q1.getValue() * q1.getUnit().getConversionFactor();
            double base2 = q2.getValue() * q2.getUnit().getConversionFactor();
            double resultBase = base1 + base2;
            double converted = resultBase / target.getUnit().getConversionFactor();
            QuantityModel<?> resultModel = new QuantityModel<>(converted, target.getUnit());
            String userName = getUserName();
            QuantityMeasurementEntity entity = new QuantityMeasurementEntity(q1, q2, "ADD", resultModel,userName);
            repository.save(entity);
            return new QuantityDTO(converted, target.getUnit().getUnitName(), target.getUnit().getMeasurementType());
        }
    }

    public QuantityDTO add(QuantityModel<?> thisQuantity, QuantityModel<?> thatQuantity , QuantityModel<?> target) throws QuantityMeasurementException {
        TemperatureUnit thisUnit = (TemperatureUnit) thisQuantity.getUnit();
        TemperatureUnit thatUnit = (TemperatureUnit) thatQuantity.getUnit();
        TemperatureUnit targetUnit = (TemperatureUnit) thatQuantity.getUnit();
        double newThisQuantityValue = thisUnit.convertTo(thisQuantity.getValue(),targetUnit);
        double thatQuantityValue = thatQuantity.getValue();
        double newValue = newThisQuantityValue + thatQuantityValue;
        QuantityModel<TemperatureUnit> resultModel = new QuantityModel<>(newValue,targetUnit);
        String userName = getUserName();
        QuantityMeasurementEntity entity = new QuantityMeasurementEntity(thisQuantity, thatQuantity, "ADD", resultModel,userName);
        repository.save(entity);
        return new QuantityDTO(newValue, target.getUnit().getUnitName(), target.getUnit().getMeasurementType());
    }


    @Override
    public QuantityDTO subtract(QuantityDTO thisQuantityDTO, QuantityDTO thatQuantityDTO) throws QuantityMeasurementException {
        QuantityModel<?> q1 = getQuantityModel(thisQuantityDTO);
        QuantityModel<?> q2 = getQuantityModel(thatQuantityDTO);
        if (!q1.getUnit().getMeasurementType().equals(q2.getUnit().getMeasurementType())) {
            throw new QuantityMeasurementException("Cannot add different measurement types");
        }

        double baseValue1 = q1.getValue() * q1.getUnit().getConversionFactor();
        double baseValue2 = q2.getValue() * q2.getUnit().getConversionFactor();
        double resultBase = baseValue1 - baseValue2;
        double resultValue = resultBase / q1.getUnit().getConversionFactor();
        QuantityModel<?> resultModel = new QuantityModel<>(resultValue, q1.getUnit());
        String userName = getUserName();
        QuantityMeasurementEntity entity = new QuantityMeasurementEntity(q1, q2, "SUBTRACT", resultModel,userName);
        repository.save(entity);
        return new QuantityDTO(resultValue, q1.getUnit().getUnitName(), q1.getUnit().getMeasurementType());
    }

    @Override
    public QuantityDTO subtract(QuantityDTO thisQuantityDTO, QuantityDTO thatQuantityDTO, QuantityDTO targetUnitDTO) throws QuantityMeasurementException {
        QuantityModel<?> q1 = getQuantityModel(thisQuantityDTO);
        QuantityModel<?> q2 = getQuantityModel(thatQuantityDTO);
        QuantityModel<?> target = getQuantityModel(targetUnitDTO);

        if (!q1.getUnit().getMeasurementType().equals(q2.getUnit().getMeasurementType())) {
            throw new QuantityMeasurementException("Cannot add different measurement types");
        }

        if(q1.getUnit().getClass().equals(TemperatureUnit.class)){
            return subtract(q1,q2,target);
        }

        double base1 = q1.getValue() * q1.getUnit().getConversionFactor();
        double base2 = q2.getValue() * q2.getUnit().getConversionFactor();
        double resultBase = base1 - base2;
        double converted = resultBase / target.getUnit().getConversionFactor();
        QuantityModel<?> resultModel = new QuantityModel<>(converted, target.getUnit());
        String userName = getUserName();
        QuantityMeasurementEntity entity = new QuantityMeasurementEntity(q1, q2, "SUBTRACT", resultModel,userName);
        repository.save(entity);
        return new QuantityDTO(converted, target.getUnit().getUnitName(), target.getUnit().getMeasurementType());
    }

    public QuantityDTO subtract(QuantityModel<?> thisQuantity, QuantityModel<?> thatQuantity , QuantityModel<?> target) throws QuantityMeasurementException {
        TemperatureUnit thisUnit = (TemperatureUnit) thisQuantity.getUnit();
        TemperatureUnit thatUnit = (TemperatureUnit) thatQuantity.getUnit();
        TemperatureUnit targetUnit = (TemperatureUnit) thatQuantity.getUnit();
        double newThisQuantityValue = thisUnit.convertTo(thisQuantity.getValue(),targetUnit);
        double thatQuantityValue = thatQuantity.getValue();
        double newValue = newThisQuantityValue - thatQuantityValue;
        QuantityModel<TemperatureUnit> resultModel = new QuantityModel<>(newValue,targetUnit);
        String userName = getUserName();
        QuantityMeasurementEntity entity = new QuantityMeasurementEntity(thisQuantity, thatQuantity, "SUBTRACT", resultModel,userName);
        repository.save(entity);
        return new QuantityDTO(newValue, target.getUnit().getUnitName(), target.getUnit().getMeasurementType());
    }

    @Override
    public double divide(QuantityDTO thisQuantityDTO, QuantityDTO thatQuantityDTO) throws QuantityMeasurementException {
        QuantityModel<?> q1 = getQuantityModel(thisQuantityDTO);
        QuantityModel<?> q2 = getQuantityModel(thatQuantityDTO);
        if (!q1.getUnit().getMeasurementType().equals(q2.getUnit().getMeasurementType())) {
            throw new QuantityMeasurementException("Cannot divide different measurement types");
        }

        double base1 = q1.getValue() * q1.getUnit().getConversionFactor();
        double base2 = q2.getValue() * q2.getUnit().getConversionFactor();
        if (base2 == 0) {
            throw new QuantityMeasurementException("Division by zero");
        }

        double result = base1 / base2;

        String userName = getUserName();
        QuantityMeasurementEntity entity = new QuantityMeasurementEntity(q1, q2, "DIVIDE", String.valueOf(result),userName);
        repository.save(entity);
        return result;
    }

    @Override
    public List<QuantityMeasurementEntity> getHistory() {
        String userName = getUserName();
        return repository.findByUserName(userName);
    }

    @Override
    public List<QuantityMeasurementEntity> findByOperation(String Operation) {
        return repository.findByOperation(Operation);
    }

    @Override
    public List<QuantityMeasurementEntity> findByThisMeasurementType(String measurementType) {
        return repository.findByThisMeasurementType(measurementType);
    }

    @Override
    public List<QuantityMeasurementEntity> findByCreatedAtAfter(LocalDateTime date) {
        return repository.findByCreatedAtAfter(date);
    }

    @Override
    public List<QuantityMeasurementEntity> findByOperationAndIsErrorFalse(String operation) {
        return repository.findByOperationAndIsErrorFalse(operation);
    }

    @Override
    public long countByOperationAndIsErrorFalse(String operation) {
        return repository.countByOperationAndIsErrorFalse(operation);
    }

    @Override
    public List<QuantityMeasurementEntity> findByIsErrorTrue() {
        return repository.findByIsErrorTrue();
    }

    @Override

    public void deleteById(Long id) {
        repository.deleteById(id);
        System.out.println("Operation with id "+id+" deleted");
    }

    public String getUserName(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getName();
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