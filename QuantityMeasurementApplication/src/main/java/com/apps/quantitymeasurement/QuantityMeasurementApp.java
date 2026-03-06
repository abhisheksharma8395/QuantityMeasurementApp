package com.apps.quantitymeasurement;

public class QuantityMeasurementApp {

    public static boolean demonstrateLengthEquality(Length length1, Length length2){
        if(length1 == null) return false;
        return length1.equals(length2);
    }

    public static boolean demonstrateLengthComparison(Length value1 , Length value2){
        if(value1 == null) return false;
        return value1.equals(value2);
    }

    public static Length demonstrateLengthConversion(double value, LengthUnit fromUnit, LengthUnit toUnit){
        Length length = new Length(value,fromUnit);
        return length.convertTo(toUnit);
    }

    public static Length demonstrateLengthConversion(Length length, LengthUnit toUnit){
        return length.convertTo(toUnit);
    }

    public static Length demonstrateLengthAddition(Length length1,Length length2){
        return length1.add(length2);
    }

    public static Length demonstrateLengthAddition(Length length1, Length length2, LengthUnit targetUnit){
        if(targetUnit == null) throw new IllegalArgumentException();
        return length1.add(length2,targetUnit);
    }


    public static void main(String[] args) {
        System.out.println(new Length(1.0,LengthUnit.FEET).convertTo(LengthUnit.INCHES));
        System.out.println(demonstrateLengthAddition(new Length(1.0,LengthUnit.FEET),new Length(12.0,LengthUnit.INCHES),LengthUnit.FEET));
        System.out.println(new Length(36.0,LengthUnit.INCHES).equals(new Length(1.0,LengthUnit.YARDS)));
        System.out.println(demonstrateLengthAddition(new Length(1.0,LengthUnit.YARDS),new Length(3.0,LengthUnit.FEET),LengthUnit.YARDS));
        System.out.println(new Length(2.54,LengthUnit.CENTIMETERS).convertTo(LengthUnit.INCHES));
        System.out.println(demonstrateLengthAddition(new Length(5.0,LengthUnit.FEET),new Length(0.0,LengthUnit.INCHES),LengthUnit.FEET));
        System.out.println(LengthUnit.FEET.convertToBaseUnit(12.0));
        System.out.println(LengthUnit.INCHES.convertToBaseUnit(12.0));

    }
}
