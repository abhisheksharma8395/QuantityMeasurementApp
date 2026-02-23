package com.apps.quantitymeasurement;

public class QuantityMeasurementApp {

    public static boolean demonstrateLengthEquality(double value1, Length.LengthUnit unit1, double value2, Length.LengthUnit unit2){
        Length length1 = new Length(value1,unit1);
        Length length2 = new Length(value2,unit2);
        return length1.equals(length2);
    }

    public static boolean demonstrateLengthComparison(Length value1 , Length value2){
        return value1.equals(value2);
    }

    public static void main(String[] args) {
        System.out.println(demonstrateLengthEquality(1.0, Length.LengthUnit.FEET, 12.0, Length.LengthUnit.INCHES));
        System.out.println(demonstrateLengthEquality(1.0, Length.LengthUnit.YARDS, 36.0, Length.LengthUnit.INCHES));
        System.out.println(demonstrateLengthEquality(100.0, Length.LengthUnit.CENTIMETERS, 39.3701, Length.LengthUnit.INCHES));
        System.out.println(demonstrateLengthEquality(3.0, Length.LengthUnit.FEET, 1.0, Length.LengthUnit.YARDS));
        System.out.println(demonstrateLengthEquality(30.48, Length.LengthUnit.CENTIMETERS, 1.0, Length.LengthUnit.FEET));
    }
}
