package com.apps.quantitymeasurement;

public enum LengthUnit implements IMeasurable{
    FEET(1.0),
    INCHES(1.0 /12),
    YARDS(3.0),
    CENTIMETERS(1/(30.48));

    SupportsArithmetic supportsArithmetic = () -> true;

    @Override
    public boolean supportsArithmetic(){
        return supportsArithmetic.isSupported();
    }

    @Override
    public void validateOperationsupports(String operation) {
        if(!supportsArithmetic.isSupported()){
            String message = this.name() + " does not support "+ operation + "operations.";
            throw new UnsupportedOperationException(message);
        }
    }


    private final double conversionFactor;

    LengthUnit(double conversionFactor){
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