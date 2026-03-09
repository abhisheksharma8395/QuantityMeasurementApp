package com.apps.quantitymeasurement;


import java.util.Objects;
import java.util.function.DoubleBinaryOperator;

public class Quantity<U extends IMeasurable> {
    private double value;
    private U unit;

    public Quantity(double value,U unit) throws IllegalArgumentException{
        if(Double.isNaN(value) || unit == null) throw new IllegalArgumentException();
        this.value = value;
        this.unit = unit;
    }

    public double getValue(){
        return this.value;
    }

    public U getUnit(){
        return this.unit;
    }

    // This method first converts the current value to the base unit , then convert the base value to target unit.
    public <U extends IMeasurable> double convertTo(U targetUnit){
        if(this.unit.getClass().equals(TemperatureUnit.class)){
            TemperatureUnit thisUnit = (TemperatureUnit) unit;
            double newValue = thisUnit.convertTo(this.value,(TemperatureUnit) targetUnit);
            return newValue;
        }
        double baseValue = this.value * this.unit.getConversionFactor();
        return baseValue / targetUnit.getConversionFactor();
    }

    public <U extends IMeasurable> Quantity<U> convert(U targetUnit){
        return new Quantity<>(convertTo(targetUnit),targetUnit);
    }


    // This method first converts the both quantity to their base unit , adds the value and then convert the sum back to the unit of this quantity.
    public Quantity<U> add(Quantity<U> other) throws UnsupportedOperationException{
        if(this.unit.supportsArithmetic()){
            validateArithmeticOperands(other,null,false);
            double newValue = performArithmetic(other,this.unit,ArithmeticOperation.ADD);
            return new Quantity<U>(newValue,this.unit);
        }
        else{
            this.unit.validateOperationsupports("ADD");
            return null;
        }
    }

    // This method first converts the both quantity to their base unit , adds the value and then convert the sum back to the unit of target unit.
    public Quantity<U> add(Quantity<U> other, U targetUnit) throws UnsupportedOperationException{
        if(this.unit.supportsArithmetic()) {
            validateArithmeticOperands(other, targetUnit, true);
            double newValue = performArithmetic(other, targetUnit, ArithmeticOperation.ADD);
            return new Quantity<U>(newValue, targetUnit);
        }
        else{
            this.unit.validateOperationsupports("ADD");
            return null;
        }
    }

    // This method first converts the both quantity to their base unit , subtract the value and then convert the result back to the unit of this quantity.
    public Quantity<U> subtract(Quantity<U> other) throws UnsupportedOperationException{
        if(this.unit.supportsArithmetic()) {
            validateArithmeticOperands(other, null, false);
            double newValue = performArithmetic(other, this.unit, ArithmeticOperation.SUBTRACT);
            return new Quantity<U>(newValue, this.unit);
        }
        else{
            this.unit.validateOperationsupports("SUBTRACT");
            return null;
        }
    }

    // This method first converts the both quantity to their base unit , subtracts the value and then convert the result back to the target unit.
    public Quantity<U> subtract(Quantity<U> other, U targetUnit) throws UnsupportedOperationException{
        if(this.unit.supportsArithmetic()) {
            validateArithmeticOperands(other, targetUnit, true);
            double newValue = performArithmetic(other, targetUnit, ArithmeticOperation.SUBTRACT);
            return new Quantity<U>(newValue, targetUnit);
        }
        else{
            this.unit.validateOperationsupports("SUBTRACT");
            return null;
        }
    }

    // This method first converts the both quantity to their base unit , return the result of their division.
    public double divide(Quantity<U> other) throws UnsupportedOperationException{
        if(this.unit.supportsArithmetic()) {
            validateArithmeticOperands(other, null, false);
            double ratio = ArithmeticOperation.DIVIDE.compute(this.unit.convertToBaseUnit(this.value), other.unit.convertToBaseUnit(other.value));
            return ratio;
        }
        else{
            this.unit.validateOperationsupports("SUBTRACT");
            return 0.0;
        }
    }


    // this method is return boolean if this quantity is equals to quantity passed in parameter after converting both to the base unit
    @Override
    public boolean equals(Object obj){
        if(obj == null) return false;
        else if (!this.unit.getClass().equals(((Quantity) obj).unit.getClass())) return false;
        else if (this == obj) return true;
        else if(!(obj instanceof Quantity<?>)) return false;
        else if(obj instanceof Quantity<?>){
            double value1 = this.unit.convertToBaseUnit(this.value);
            double value2 = ((Quantity) obj).unit.convertToBaseUnit(((Quantity) obj).value);
            return ((Double.compare(value1,value2)) == 0);
        }
        return false;
    }

    @Override
    public int hashCode() {
        double baseValue = this.value * this.unit.getConversionFactor();
        return Objects.hash(baseValue);
    }

    @Override
    public String toString(){
        return this.value+" "+this.unit;
    }

    private void validateArithmeticOperands(Quantity<U> other , U targetUnit , boolean targetUnitRequired){
        if(other == null) throw new IllegalArgumentException();
        if(!Double.isFinite(other.getValue()) || !Double.isFinite(this.getValue())) throw new IllegalArgumentException();
        else if(targetUnitRequired && (targetUnit == null)) throw new IllegalArgumentException();
    }

    private enum ArithmeticOperation{
        ADD((a,b) -> a+b),
        SUBTRACT((a,b) -> a-b),
        DIVIDE((a,b) -> {
            if(b == 0.0) throw new ArithmeticException("Divide by zero");
            return a/b;
        });

        private final DoubleBinaryOperator operation;

        ArithmeticOperation(DoubleBinaryOperator operation){
            this.operation = operation;
        }

        public double compute(double a , double b){
            return operation.applyAsDouble(a,b);
        }
    }

    private double performArithmetic(Quantity<U> other , U targetUnit , ArithmeticOperation operation){
        double thisToBase = this.unit.convertToBaseUnit(this.value);
        double otherToBase = other.unit.convertToBaseUnit(other.value);
        double valueAfterOperation = operation.compute(thisToBase,otherToBase);
        return targetUnit.convertFromBaseUnit(valueAfterOperation);
    }

    public static void main(String[] args) {
        System.out.println(new Quantity<>(0.0, TemperatureUnit.CELSIUS).equals(new Quantity<>(32.0, TemperatureUnit.FAHRENHEIT)));
        System.out.println(new Quantity<>(273.15, TemperatureUnit.KELVIN).equals(new Quantity<>(0.0, TemperatureUnit.CELSIUS)));
        System.out.println(new Quantity<>(212.0, TemperatureUnit.FAHRENHEIT).equals(new Quantity<>(100.0, TemperatureUnit.CELSIUS)));
        System.out.println(new Quantity<>(100.0, TemperatureUnit.CELSIUS).equals(new Quantity<>(373.15, TemperatureUnit.KELVIN)));
        System.out.println(new Quantity<>(50.0, TemperatureUnit.CELSIUS).equals(new Quantity<>(122.0, TemperatureUnit.FAHRENHEIT)));

        System.out.println(new Quantity<>(100.0, TemperatureUnit.CELSIUS).convertTo(TemperatureUnit.FAHRENHEIT));
        System.out.println(new Quantity<>(32.0, TemperatureUnit.FAHRENHEIT).convertTo(TemperatureUnit.CELSIUS));
        System.out.println(new Quantity<>(273.15, TemperatureUnit.KELVIN).convertTo(TemperatureUnit.CELSIUS));
        System.out.println(new Quantity<>(0.0, TemperatureUnit.CELSIUS).convertTo(TemperatureUnit.KELVIN));
        System.out.println(new Quantity<>(-40.0, TemperatureUnit.CELSIUS).convertTo(TemperatureUnit.FAHRENHEIT));

        try {
            new Quantity<>(100.0, TemperatureUnit.CELSIUS).add(new Quantity<>(50.0, TemperatureUnit.CELSIUS));
        } catch (UnsupportedOperationException e) {
            System.out.println(e.getMessage());
        }

        try {
            new Quantity<>(100.0, TemperatureUnit.CELSIUS).subtract(new Quantity<>(50.0, TemperatureUnit.CELSIUS));
        } catch (UnsupportedOperationException e) {
            System.out.println(e.getMessage());
        }

        try {
            new Quantity<>(100.0, TemperatureUnit.CELSIUS).divide(new Quantity<>(50.0, TemperatureUnit.CELSIUS));
        } catch (UnsupportedOperationException e) {
            System.out.println(e.getMessage());
        }

        System.out.println(new Quantity<>(100.0, TemperatureUnit.CELSIUS).equals(new Quantity<>(100.0, LengthUnit.FEET)));
    }
}
