package com.apps.quantitymeasurement;

public enum VolumeUnit implements IMeasurable{
    LITRE(1.0),
    MILLILITRE(1.0 /1000),
    GALLON(3.78541);

    private final double conversionFactor;
    VolumeUnit(double conversionFactor){
        this.conversionFactor = conversionFactor;
    }

    public double getConversionFactor(){
        return conversionFactor;
    }


    public double convertToBaseUnit(double value){
        double convertValue = value * getConversionFactor();
        return Math.round(convertValue * 100.0) / 100.0;
    }

    public double convertFromBaseUnit(double value){
        double convertValue = value / getConversionFactor();
        return Math.round(convertValue * 100.0) / 100.0;
    }
}