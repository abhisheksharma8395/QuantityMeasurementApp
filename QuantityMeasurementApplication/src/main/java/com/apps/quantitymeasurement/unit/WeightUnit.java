package com.apps.quantitymeasurement.unit;

import com.apps.quantitymeasurement.SupportsArithmetic;

public enum WeightUnit implements IMeasurableUnit {

    MILLIGRAM,
    GRAM,
    KILOGRAM,
    POUND,
    TONNE;

    @Override
    public String getUnitName() {
        return this.name();
    }

    @Override
    public String getMeasurementType() {
        return this.getClass().getSimpleName();
    }
}
