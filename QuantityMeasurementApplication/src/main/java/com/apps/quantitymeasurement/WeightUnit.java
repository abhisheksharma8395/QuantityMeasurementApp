package com.apps.quantitymeasurement;

public enum WeightUnit {

    MILLIGRAM(0.001),
    GRAM(1.0),
    KILOGRAM(1000.0),
    POUND(453.592),
    TONNE(1_000_000.0);

    private final double conversionFactor;

    WeightUnit(double conversionFactor){
        this.conversionFactor = conversionFactor;
    }

    public double getConversionFactor(){
        return conversionFactor;
    }


    public double convertToBaseUnit(double value){
        double convertValue = value * getConversionFactor();
        return Math.round(convertValue * 100.0) / 100.0;
    }

    public double convertFromBaseUnit(double value){
        double convertValue = value / this.getConversionFactor();
        return Math.round(convertValue * 100.0) / 100.0;
    }

    public static void main(String[] args) {
        double kilograms = 10.0;
        double grams = WeightUnit.KILOGRAM.convertToBaseUnit(kilograms);
        System.out.println(kilograms+" kilogram is "+grams+" grams.");

        double milligrams = WeightUnit.MILLIGRAM.convertFromBaseUnit(grams);
        System.out.println(grams+" gram is "+milligrams+" milligrams.");
    }
}
