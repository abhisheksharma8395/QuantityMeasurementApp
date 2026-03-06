package com.apps.quantitymeasurement;

public class Length {


    private double value;
    private LengthUnit unit;

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

    public static void main(String[] args) {
        Length length1 = new Length(1.0,LengthUnit.FEET);
        Length length2 = new Length(12.0,LengthUnit.INCHES);
        System.out.println("Are lengths equal? "+length1.equals(length2));

        Length length3 = new Length(1.0,LengthUnit.YARDS);
        Length length4 = new Length(36.0,LengthUnit.INCHES);
        System.out.println("Are lengths equal? "+length3.equals(length4));

        Length length5 = new Length(100.0,LengthUnit.CENTIMETERS);
        Length length6 = new Length(39.3701,LengthUnit.INCHES);
        System.out.println("Are lengths equal? "+length5.equals(length6));
    }
}
