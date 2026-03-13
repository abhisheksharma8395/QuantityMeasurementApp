package com.apps.quantitymeasurement.unit;

public enum LengthUnit implements IMeasurableUnit {
    FEET(1.0),
    INCHES(1.0/12),
    YARDS(3.0),
    CENTIMETERS();

    private double conversionFactor;

    LengthUnit(double conversionFactor){
        this.conversionFactor = conversionFactor;
    }

    @Override
    public double getConversionFactor() {
        return 0;
    }

    @Override
    public String getUnitName() {
        return this.name();
    }

    @Override
    public String getMeasurementType() {
        return this.getClass().getSimpleName();
    }
}