package com.apps.quantitymeasurement;
import java.util.function.Function;

public enum TemperatureUnit implements IMeasurable{
    CELSIUS(false),
    FAHRENHEIT(true),
    KELVIN(false);

    final boolean isFahrenheit;

    TemperatureUnit(boolean isFahrenheit){
        this.isFahrenheit = isFahrenheit;
    }

    SupportsArithmetic supportsArithmetic = () -> false;

    Function<Double,Double> coversionValue;
    final Function<Double,Double> FAHRENHEIT_TO_CELSIUS = (fahrenheit) -> (fahrenheit - 32) * (5.0 / 9.0) ;
    final Function<Double,Double> CELSIUS_TO_FAHRENHEIT = (celsius) -> (celsius * (9.0/5.0)) + 32;
    final Function<Double,Double> CELSIUS_TO_KELVIN = (celsius) -> (celsius + 273.15);
    final Function<Double,Double> KELVIN_TO_CELSIUS = (celsius) -> (celsius - 273.15);
    final Function<Double,Double> FAHRENHEIT_TO_KELVIN = (fahrenheit) -> (CELSIUS_TO_KELVIN.apply(FAHRENHEIT_TO_CELSIUS.apply(fahrenheit)));
    final Function<Double,Double> KELVIN_TO_FAHRENHEIT = (kelvin) -> (CELSIUS_TO_FAHRENHEIT.apply(KELVIN_TO_CELSIUS.apply(kelvin)));

    @Override
    public double getConversionFactor() {
        return 1.0;
    }

    @Override
    public double convertToBaseUnit(double value) {
        return convertTo(value,TemperatureUnit.CELSIUS);
    }

    @Override
    public double convertFromBaseUnit(double value) {
        if(this == FAHRENHEIT) return CELSIUS_TO_FAHRENHEIT.apply(value);
        else if (this == KELVIN) return CELSIUS_TO_KELVIN.apply(value);
        else if(this == CELSIUS) return value;
        return 0.0;
    }


    public double convertTo(double value , TemperatureUnit targetUnit){
        if(this == FAHRENHEIT){
            if(targetUnit == CELSIUS){
                return this.FAHRENHEIT_TO_CELSIUS.apply(value);
            } else if (targetUnit == FAHRENHEIT) {
                return value;
            } else if(targetUnit == KELVIN){
                return this.FAHRENHEIT_TO_KELVIN.apply(value);
            }
        }
        else if(this == CELSIUS){
            if(targetUnit == FAHRENHEIT){
                return this.CELSIUS_TO_FAHRENHEIT.apply(value);
            } else if (targetUnit == CELSIUS) {
                return value;
            } else if(targetUnit == KELVIN){
                return this.CELSIUS_TO_KELVIN.apply(value);
            }
        }
        else if(this == KELVIN){
            if(targetUnit == FAHRENHEIT){
                return this.KELVIN_TO_FAHRENHEIT.apply(value);
            } else if (targetUnit == CELSIUS) {
                return KELVIN_TO_CELSIUS.apply(value);
            } else if(targetUnit == KELVIN){
                return value;
            }
        }
        return 0.0;
    }

    @Override
    public boolean supportsArithmetic() {
        return supportsArithmetic.isSupported();
    }

    @Override
    public void validateOperationsupports(String operation) {
        if(!supportsArithmetic.isSupported()){
            String message = this.name() + " does not support "+ operation + " operations.";
            throw new UnsupportedOperationException(message);
        }
    }

    public static void main(String[] args) {
        System.out.println("TemperatureUnit Enum");
        for(TemperatureUnit unit : TemperatureUnit.values()){
            System.out.println(unit + " has conversion function to base unit : "+ unit.coversionValue);
        }
        System.out.println("Does TemperatureUnit support arithmetic operation? " +
                TemperatureUnit.CELSIUS.supportsArithmetic() + " for CELSIUS, "+
                TemperatureUnit.FAHRENHEIT.supportsArithmetic() + " for FAHRENHEIT.");
    }

}
