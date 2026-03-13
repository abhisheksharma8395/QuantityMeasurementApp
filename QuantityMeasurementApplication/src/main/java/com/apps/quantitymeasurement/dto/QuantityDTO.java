package com.apps.quantitymeasurement.dto;

import com.apps.quantitymeasurement.unit.IMeasurableUnit;

public class QuantityDTO {
    public double value;
    public String unit;
    public String measurementType;

    public QuantityDTO(double value , IMeasurableUnit unit) {
        this.value = value;
        this.unit = unit.getUnitName();
        this.measurementType = unit.getMeasurementType();
    }

    public QuantityDTO(double value, String unit, String measurementType) {
        this.value = value;
        this.unit = unit;
        this.measurementType = measurementType;
    }

    public double getValue(){
        return this.value;
    }

    public String getUnit(){
        return this.unit;
    }

    public String getMeasurementType(){
        return this.measurementType;
    }

    @Override
    public String toString(){
        return this.value+" "+this.measurementType+" "+this.unit;
    }
}
