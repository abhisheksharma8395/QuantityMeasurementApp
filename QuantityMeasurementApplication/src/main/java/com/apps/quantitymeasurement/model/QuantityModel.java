package com.apps.quantitymeasurement.model;

import com.apps.quantitymeasurement.unit.IMeasurableUnit;

public class QuantityModel<U extends IMeasurableUnit> {
    public double value;
    public U unit;

    public QuantityModel(double value , U unit) {
        this.value = value;
        this.unit = unit;
    }

    public double getValue(){
        return this.getValue();
    }

    public U getUnit(){
        return this.unit;
    }

    @Override
    public String toString(){
        return this.value+" "+this.unit;
    }
}
