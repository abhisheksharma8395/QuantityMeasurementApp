package com.apps.quantitymeasurement.unit;

public enum TemperatureUnit implements IMeasurableUnit {
    CELSIUS,
    FAHRENHEIT,
    KELVIN;

    @Override
    public String getUnitName() {
        return this.name();
    }

    @Override
    public String getMeasurementType() {
        return this.getClass().getSimpleName();
    }

}
