package com.apps.quantitymeasurement;

public class Length {


    private double value;
    private LengthUnit unit;

    public enum LengthUnit{
        FEET(12.0),
        INCHES(1.0);

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
    }
}
