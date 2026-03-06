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

    public static Length demonstrateLengthAddition(Length length1,Length length2){
        return length1.add(length2);
    }

    public static Length demonstrateLengthAddition(Length length1, Length length2, Length.LengthUnit targetUnit){
        if(targetUnit == null) throw new IllegalArgumentException();
        return length1.add(length2,targetUnit);
    }


    public static void main(String[] args) {
        System.out.println(demonstrateLengthAddition(new Length(1.0, Length.LengthUnit.FEET), new Length(12.0, Length.LengthUnit.INCHES), Length.LengthUnit.FEET));
        System.out.println(demonstrateLengthAddition(new Length(1.0, Length.LengthUnit.FEET), new Length(12.0, Length.LengthUnit.INCHES), Length.LengthUnit.INCHES));
        System.out.println(demonstrateLengthAddition(new Length(1.0, Length.LengthUnit.FEET), new Length(12.0, Length.LengthUnit.INCHES), Length.LengthUnit.YARDS));
        System.out.println(demonstrateLengthAddition(new Length(1.0, Length.LengthUnit.YARDS), new Length(3.0, Length.LengthUnit.FEET), Length.LengthUnit.YARDS));
        System.out.println(demonstrateLengthAddition(new Length(36.0, Length.LengthUnit.INCHES), new Length(1.0, Length.LengthUnit.YARDS), Length.LengthUnit.FEET));
        System.out.println(demonstrateLengthAddition(new Length(2.54, Length.LengthUnit.CENTIMETERS), new Length(1.0, Length.LengthUnit.INCHES), Length.LengthUnit.CENTIMETERS));
        System.out.println(demonstrateLengthAddition(new Length(5.0, Length.LengthUnit.FEET), new Length(0.0, Length.LengthUnit.INCHES), Length.LengthUnit.YARDS));
        System.out.println(demonstrateLengthAddition(new Length(5.0, Length.LengthUnit.FEET), new Length(-2.0, Length.LengthUnit.FEET), Length.LengthUnit.INCHES));

    }
}
