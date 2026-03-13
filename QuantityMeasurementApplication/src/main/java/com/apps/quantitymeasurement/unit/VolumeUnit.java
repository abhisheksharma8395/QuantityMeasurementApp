package com.apps.quantitymeasurement.unit;

public enum VolumeUnit implements IMeasurableUnit {
    LITRE,
    MILLILITRE,
    GALLON;

    @Override
    public String getUnitName() {
        return this.name();
    }

    @Override
    public String getMeasurementType() {
        return this.getClass().getSimpleName();
    }
}