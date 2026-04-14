package com.apps.quantitymeasurement.unit;

public enum VolumeUnit implements IMeasurableUnit {
    LITER(1.0),
    MILLILITER(1.0/1000),
    GALLON(3.7854118);

    private double conversionFactor;

    VolumeUnit(double conversionFactor){
        this.conversionFactor = conversionFactor;
    }

    @Override
    public double getConversionFactor() {
        return this.conversionFactor;
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