package com.apps.quantitymeasurement;

public class Weight {
    private double value;
    private WeightUnit unit;

    public double getValue() {
        return value;
    }

    public WeightUnit getUnit() {
        return unit;
    }

    public Weight(double value , WeightUnit unit){
        if (Double.isNaN(value) || Double.isInfinite(value)) {
            throw new IllegalArgumentException("Length value must be a finite number.");
        }
        if (unit == null) {
            throw new IllegalArgumentException("Length unit must not be null.");
        }
        this.value = value;
        this.unit = unit;
    }

    public double convertToBaseUnit(){
        double convertedIntoBaseUnit = this.value * this.unit.getConversionFactor();
        return convertedIntoBaseUnit;
    }

    public boolean compare(Weight thatLength){
        if(thatLength == null) return false;
        if(Math.abs(this.convertToBaseUnit() - thatLength.convertToBaseUnit()) < 0.0001) return true;
        return false;
    }

    @Override
    public boolean equals(Object o){
        if(o == null) return false;
        if(!(o instanceof Weight)) return false;
        return compare((Weight) o);
    }

    public Weight convertTo(WeightUnit targetUnit) throws IllegalArgumentException{
        if(targetUnit == null) throw new IllegalArgumentException();
        double baseValue = convertToBaseUnit();
        double convertedValue = baseValue/ targetUnit.getConversionFactor();
        return new Weight(convertedValue,targetUnit);
    }

    public Weight add(Weight thatWeight){
        double thisToBaseUnit = this.convertToBaseUnit();
        double thatToBaseUnit = thatWeight.convertToBaseUnit();
        double totalBaseUnit = thisToBaseUnit + thatToBaseUnit;
        double newValue = totalBaseUnit / this.unit.getConversionFactor();
        return new Weight(newValue,this.unit);
    }

    public Weight add(Weight weight,WeightUnit targetUnit){
        return addAndConvert(weight,targetUnit);
    }

    private Weight addAndConvert(Weight weight , WeightUnit targetUnit){
        double thisToBaseUnit = this.convertToBaseUnit();
        double thatToBaseUnit = weight.convertToBaseUnit();
        double totalBaseUnit = thisToBaseUnit + thatToBaseUnit;
        double newValue = totalBaseUnit / targetUnit.getConversionFactor();
        return new Weight(newValue,targetUnit);
    }

    private double convertFromBaseToTargetUnit(double weightInGrams,WeightUnit targetUnit){
        double newValue = weightInGrams/targetUnit.getConversionFactor();
        return newValue;
    }

    @Override
    public String toString(){
        return String.format("%.2f %s",this.value,this.unit);
    }

    public static void main(String[] args) {
        Weight weight1 = new Weight(3.0,WeightUnit.KILOGRAM);
        Weight weight2 = new Weight(2.0,WeightUnit.MILLIGRAM);

        System.out.println(weight1);
        System.out.println(weight2);

    }
}
