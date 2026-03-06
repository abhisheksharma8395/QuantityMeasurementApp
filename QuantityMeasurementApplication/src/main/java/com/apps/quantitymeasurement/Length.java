package com.apps.quantitymeasurement;

public class Length {


    private double value;
    private LengthUnit unit;

    public double getValue() {
        return value;
    }

    public LengthUnit getUnit() {
        return unit;
    }

    public enum LengthUnit{
        FEET(12.0),
        INCHES(1.0),
        YARDS(36.0),
        CENTIMETERS(0.393701);


        private final double conversionFactor;

        LengthUnit(double conversionFactor){
            this.conversionFactor = conversionFactor;
        }

        public double getConversionFactor(){
            return conversionFactor;
        }


    }

    public Length(double value , LengthUnit unit){
        this.value = value;
        this.unit = unit;
    }

    public double convertToBaseUnit(){
        double convertedIntoBaseUnit = this.value * this.unit.getConversionFactor();
        return convertedIntoBaseUnit;
    }

    public boolean compare(Length thatLength){
        if(thatLength == null) return false;
        if(Math.abs(this.convertToBaseUnit() - thatLength.convertToBaseUnit()) < 0.0001) return true;
        return false;
    }

    @Override
    public boolean equals(Object o){
        if(o == null) return false;
        if(!(o instanceof Length)) return false;
        return compare((Length) o);
    }

    public Length convertTo(LengthUnit targetUnit) throws IllegalArgumentException{
        if(targetUnit == null) throw new IllegalArgumentException();
        double baseValue = convertToBaseUnit();
        double convertedValue = baseValue/ targetUnit.getConversionFactor();
        return new Length(convertedValue,targetUnit);
    }

    public Length add(Length thatLength){
        double thisToBaseUnit = this.convertToBaseUnit();
        double thatToBaseUnit = thatLength.convertToBaseUnit();
        double totalBaseUnit = thisToBaseUnit + thatToBaseUnit;
        double newValue = totalBaseUnit / this.unit.getConversionFactor();
        return new Length(newValue,this.unit);
    }

    public Length add(Length length,LengthUnit targetUnit){
        return addAndConvert(length,targetUnit);
    }

    private Length addAndConvert(Length length , LengthUnit targetUnit){
        double thisToBaseUnit = this.convertToBaseUnit();
        double thatToBaseUnit = length.convertToBaseUnit();
        double totalBaseUnit = thisToBaseUnit + thatToBaseUnit;
        double newValue = totalBaseUnit / targetUnit.getConversionFactor();
        return new Length(newValue,targetUnit);
    }

    private double convertFromBaseToTargetUnit(double lengthInInches,LengthUnit targetUnit){
        double newValue = lengthInInches/targetUnit.getConversionFactor();
        return newValue;
    }

    @Override
    public String toString(){
        return String.format("%.2f %s",this.value,this.unit);
    }

    public static void main(String[] args) {
        Length length1 = new Length(3.0,LengthUnit.FEET);
        Length length2 = new Length(2.0,LengthUnit.YARDS);

        Length length3 = length1.convertTo(LengthUnit.INCHES);
        Length length4 = length2.convertTo(LengthUnit.INCHES);
        System.out.println(length3);
        System.out.println(length4);

    }
}
