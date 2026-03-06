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

    public static <U extends IMeasurable> Quantity<U> demonstrateSubtraction(Quantity<U> quantity1, Quantity<U> quantity2) {
        return quantity1.add(quantity2);
    }

    public static <U extends IMeasurable> Quantity<U> demonstrateSubtraction(Quantity<U> quantity1, Quantity<U> quantity2, U targetUnit) {
        if (targetUnit == null) throw new IllegalArgumentException();
        return quantity1.add(quantity2, targetUnit);
    }

    public static <U extends IMeasurable> double demonstrateDivison(Quantity<U> quantity1, Quantity<U> quantity2, U targetUnit) {
        if (targetUnit == null) throw new IllegalArgumentException();
        return quantity1.divide(quantity2);
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

        // Demonstration equality between the two quantities
        Quantity<VolumeUnit> volumeInLitre = new Quantity<>(1.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> volumeInMillilitre = new Quantity<>(1000.0, VolumeUnit.MILLILITRE);
        boolean areVolumesEqual = demonstrateEquality(volumeInLitre, volumeInMillilitre);
        System.out.println("Are volumes equal? " + areVolumesEqual);


        // Demonstration conversion between the two quantities
        Quantity<VolumeUnit> convertedVolume = demonstrateConversion(volumeInLitre, VolumeUnit.MILLILITRE);
        System.out.println("Converted Volume: " + convertedVolume.getValue() + " " + convertedVolume.getUnit());


        // Demonstration addition of two quantities (result in first unit)
        Quantity<VolumeUnit> volumeInGallon = new Quantity<>(1.0, VolumeUnit.GALLON);
        Quantity<VolumeUnit> litreEquivalentOfGallon = new Quantity<>(3.78541, VolumeUnit.LITRE);
        Quantity<VolumeUnit> sumVolume = demonstrateAddition(volumeInGallon, litreEquivalentOfGallon);
        System.out.println("Sum Volume: " + sumVolume.getValue() + " " + sumVolume.getUnit());

        // Demonstration addition with specified target unit
        Quantity<VolumeUnit> sumVolumeInMillilitre = demonstrateAddition(volumeInLitre, volumeInMillilitre, VolumeUnit.MILLILITRE);
        System.out.println("Sum Volume in Millilitre: " + sumVolumeInMillilitre.getValue() + " " + sumVolumeInMillilitre.getUnit());

        Quantity<VolumeUnit> litre = new Quantity<>(1.0,VolumeUnit.LITRE);
        Quantity<VolumeUnit> millilitre = new Quantity<>(2000.0,VolumeUnit.MILLILITRE);

        Quantity<VolumeUnit> gallon = new Quantity<>(1.0,VolumeUnit.GALLON);
        Quantity<VolumeUnit> litreVolume = new Quantity<>(3.78541,VolumeUnit.LITRE);

        System.out.println(millilitre.divide(litre));
        System.out.println(gallon.subtract(litreVolume));
    }
}

