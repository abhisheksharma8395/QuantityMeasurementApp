package com.apps.quantitymeasurement;

public interface IMeasurable {

    SupportsArithmetic supportsArithmetic = () -> true;

    // Return the conversion factor to the base unit (grams)
    public double getConversionFactor();

    // this method convert the value passed in method parameter to Base unit
    public double convertToBaseUnit(double value);

    // this method convert the base unit value passed in method parameter to current unit
    public double convertFromBaseUnit(double value);

    default boolean supportsArithmetic() {
        return supportsArithmetic.isSupported();
    }

    default void validateOperationsupports(String operation){

    }
}
