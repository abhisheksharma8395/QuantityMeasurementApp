package com.apps.quantitymeasurement;

public class QuantityMeasurementApp {

    // This method demonstrates  the equality between two quantities
    public static <U extends IMeasurable> boolean demonstrateEquality(Quantity<U> quantity1, Quantity<U> quantity2) {
        if (quantity1 == null) return false;
        return quantity1.equals(quantity2);
    }

    // This method demonstrate the conversion of a quantity to target unit
    public static <U extends IMeasurable> Quantity<U> demonstrateConversion(Quantity<U> quantity, U targetUnit) {
        double baseValue = quantity.convertTo(targetUnit);
        double actualValue = baseValue / targetUnit.getConversionFactor();
        Quantity<U> convertedWeight = new Quantity<>(baseValue, targetUnit);
        return convertedWeight;
    }


    public static <U extends IMeasurable> Quantity<U> demonstrateAddition(Quantity<U> quantity1, Quantity<U> quantity2) {
        return quantity1.add(quantity2);
    }

    public static <U extends IMeasurable> Quantity<U> demonstrateAddition(Quantity<U> quantity1, Quantity<U> quantity2, U targetUnit) {
        if (targetUnit == null) throw new IllegalArgumentException();
        return quantity1.add(quantity2, targetUnit);
    }

    public static void main(String[] args) {
        // Demonstration equality between the two quantities
        Quantity<WeightUnit> weightInGrams = new Quantity<>(1000.0, WeightUnit.GRAM);
        Quantity<WeightUnit> weightInKilograms = new Quantity<>(1.0, WeightUnit.KILOGRAM);
        boolean areEqual = demonstrateEquality(weightInGrams, weightInKilograms);
        System.out.println("Are weights equal? " + areEqual);

        // Demonstration conversion between the two quantities
        Quantity<WeightUnit> convertedWeight = demonstrateConversion(weightInGrams, WeightUnit.KILOGRAM);
        System.out.println("Converted Weight: " + convertedWeight.getValue() + " " + convertedWeight.getUnit());

        // Demonstration addition of two quantities (result in first unit)
        Quantity<WeightUnit> weightInPounds = new Quantity<>(2.20462, WeightUnit.POUND);
        Quantity<WeightUnit> sumWeight = demonstrateAddition(weightInKilograms, weightInPounds);
        System.out.println("Sum Weight: " + sumWeight.getValue() + " " + sumWeight.getUnit());

        // Demonstration addition with specified target unit
        Quantity<WeightUnit> sumWeightInGrams = demonstrateAddition(weightInKilograms, weightInPounds, WeightUnit.GRAM);
        System.out.println("Sum Weight in Grams: " + sumWeightInGrams.getValue() + " " + sumWeightInGrams.getUnit());
    }
}

