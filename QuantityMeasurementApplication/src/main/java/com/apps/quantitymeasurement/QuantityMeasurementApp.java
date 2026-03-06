package com.apps.quantitymeasurement;

public class QuantityMeasurementApp {

    public static boolean demonstrateLengthEquality(Length length1,Length length2){
        return length1.equals(length2);
    }

    public static void demonstrateFeetEquality(){
        Length feet1 = new Length(5.6, Length.LengthUnit.FEET);
        Length feet2 = new Length(5.6, Length.LengthUnit.FEET);
        System.out.println(feet1.equals(feet2));
    }

    public static void demonstrateInchesEquality(){
        Length inches1 = new Length(7.0, Length.LengthUnit.INCHES);
        Length inches2 = new Length(7.0, Length.LengthUnit.INCHES);
        System.out.println(inches1.equals(inches2));
    }

    public static void demonstrateFeetInchesEquality(){
        Length feet1 = new Length(5.6, Length.LengthUnit.FEET);
        Length inches1 = new Length(67.2, Length.LengthUnit.INCHES);
        System.out.println(feet1.equals(inches1));
    }

    public static void main(String[] args) {
        demonstrateFeetEquality();
        demonstrateInchesEquality();
        demonstrateFeetInchesEquality();
        System.out.println(demonstrateLengthEquality(new Length(4.0, Length.LengthUnit.FEET), new Length(48.0, Length.LengthUnit.INCHES)));
    }
}
