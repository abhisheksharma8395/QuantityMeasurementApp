package com.apps.quantitymeasurement;


import java.util.Objects;

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
        double baseValue = this.value * this.unit.getConversionFactor();
        return baseValue / targetUnit.getConversionFactor();
    }

    public <U extends IMeasurable> Quantity<U> convert(U targetUnit){
        double baseValue = this.value * this.unit.getConversionFactor();
        return new Quantity<>(baseValue / targetUnit.getConversionFactor(),targetUnit);
    }


    // This method first converts the both quantity to their base unit , adds the value and then convert the sum back to the unit of this quantity.
    public Quantity<U> add(Quantity<U> other){
        double value1 = this.unit.convertToBaseUnit(this.value);
        double value2 = other.unit.convertToBaseUnit(other.value);
        double convertedValue = (value1 + value2)/this.unit.getConversionFactor();
        return new Quantity<>(convertedValue,this.unit);
    }

    // This method first converts the both quantity to their base unit , adds the value and then convert the sum back to the unit of target unit.
    public Quantity<U> add(Quantity<U> other, U targetUnit){
        if(other == null || targetUnit == null) throw new IllegalArgumentException();
        double value1 = this.unit.convertToBaseUnit(this.value);
        double value2 = other.unit.convertToBaseUnit(other.value);
        double convertedValue = (value1 + value2)/targetUnit.getConversionFactor();
        return new Quantity<>(convertedValue,targetUnit);
    }

    // This method first converts the both quantity to their base unit , subtract the value and then convert the result back to the unit of this quantity.
    public Quantity<U> subtract(Quantity<U> other){
        if(other == null) throw new IllegalArgumentException();
        double value1 = this.unit.convertToBaseUnit(this.value);
        double value2 = other.unit.convertToBaseUnit(other.value);
        double convertedValue = (value1 - value2)/this.unit.getConversionFactor();
        return new Quantity<>(convertedValue,this.unit);
    }

    // This method first converts the both quantity to their base unit , subtracts the value and then convert the result back to the target unit.
    public Quantity<U> subtract(Quantity<U> other, U targetUnit){
        if(other == null || targetUnit == null) throw new IllegalArgumentException();
        double value1 = this.unit.convertToBaseUnit(this.value);
        double value2 = other.unit.convertToBaseUnit(other.value);
        double convertedValue = (value1 - value2)/targetUnit.getConversionFactor();
        return new Quantity<>(convertedValue,targetUnit);
    }

    // This method first converts the both quantity to their base unit , return the result of their division.
    public double divide(Quantity<U> other){
        if(other == null) throw new IllegalArgumentException();
        else if(other.value == 0) throw new ArithmeticException();
        double value1 = this.unit.convertToBaseUnit(this.value);
        double value2 = other.unit.convertToBaseUnit(other.value);
        return value1/value2;
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

    public static void main(String[] args) {

        Quantity<LengthUnit> lengthInFeet = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> lengthInInches = new Quantity<>(120.0, LengthUnit.INCHES);
        boolean isEqual = lengthInFeet.equals(lengthInInches);
        System.out.println("Are lengths equal? " + isEqual);

        Quantity<WeightUnit> weightInKilograms = new Quantity<>(1.0, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> weightInGrams = new Quantity<>(1000.0, WeightUnit.GRAM);
        isEqual = weightInKilograms.equals(weightInGrams); // true
        System.out.println("Are weights equal? " + isEqual);

        double convertedLength = lengthInFeet.convertTo(LengthUnit.INCHES);
        System.out.println("10 feet in inches: " + convertedLength);

        Quantity<LengthUnit> totalLength = lengthInFeet.add(lengthInInches, LengthUnit.FEET);
        System.out.println("Total length in feet: " + totalLength.getValue() + " " + totalLength.getUnit());

        Quantity<WeightUnit> weightInPounds = new Quantity<>(2.0, WeightUnit.POUND);
        Quantity<WeightUnit> totalWeight = weightInKilograms.add(weightInPounds, WeightUnit.KILOGRAM);
        System.out.println("Total weight in kilograms: " + totalWeight.getValue() + " " + totalWeight.getUnit());

    }
}
