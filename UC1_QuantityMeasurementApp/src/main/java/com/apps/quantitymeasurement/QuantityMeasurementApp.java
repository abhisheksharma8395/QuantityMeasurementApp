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

    public static Length demonstrateLengthConversion(double value, Length.LengthUnit fromUnit, Length.LengthUnit toUnit){
        Length length = new Length(value,fromUnit);
        return length.convertTo(toUnit);
    }

    public static Length demonstrateLengthConversion(Length length, Length.LengthUnit toUnit){
        return length.convertTo(toUnit);
    }

    public static void main(String[] args) {
        System.out.println(demonstrateLengthConversion(1.0, Length.LengthUnit.FEET, Length.LengthUnit.INCHES));
        System.out.println(demonstrateLengthConversion(3.0, Length.LengthUnit.YARDS, Length.LengthUnit.FEET));
        System.out.println(demonstrateLengthConversion(36.0, Length.LengthUnit.INCHES, Length.LengthUnit.YARDS));
        System.out.println(demonstrateLengthConversion(1.0, Length.LengthUnit.CENTIMETERS, Length.LengthUnit.INCHES));
        System.out.println(demonstrateLengthConversion(0.0, Length.LengthUnit.FEET, Length.LengthUnit.INCHES));

    }
}
