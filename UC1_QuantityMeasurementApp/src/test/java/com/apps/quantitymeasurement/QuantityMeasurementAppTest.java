package com.apps.quantitymeasurement;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest {

    @Test
    void preventCrossTypeComparisonLengthVsWeight() {
        Quantity<LengthUnit> length = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<WeightUnit> weight = new Quantity<>(1.0, WeightUnit.KILOGRAM);
        assertNotEquals(length,weight);
    }

    @Test
    void testGenericTypeSafetyWithWeight() {
        Quantity<WeightUnit> weight = new Quantity<>(1000.0, WeightUnit.GRAM);
        Quantity<WeightUnit> converted = weight.convert(WeightUnit.KILOGRAM);
        assertEquals(1.0, converted.getValue(), 0.0001);
    }

    @Test
    void backwardCompatibilityConvertWeightKilogramsToGrams() {
        weightKilogramEqualsGrams();
    }

    @Test
    void backwardCompatibilityLengthFeetEqualsInches() {
        lengthFeetEqualsInches();
    }

    @Test
    void backwardCompatibilityChainedAdditionsLength() {
        Quantity<LengthUnit> feet = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> result = feet.add(new Quantity<>(12, LengthUnit.INCHES), LengthUnit.FEET).add(new Quantity<>(12, LengthUnit.INCHES), LengthUnit.FEET);
        assertEquals(3.0, result.getValue(), 0.0001);
    }

    @Test
    void addLengthYardsAndFeet() {
        Quantity<LengthUnit> yard = new Quantity<>(1.0, LengthUnit.YARDS);
        Quantity<LengthUnit> feet = new Quantity<>(3.0, LengthUnit.FEET);
        Quantity<LengthUnit> result = yard.add(feet, LengthUnit.YARDS);
        assertEquals(2.0, result.getValue(), 0.0001);
    }

    @Test
    void convertVolumeLitresToMilliliters() {
        Quantity<VolumeUnit> volume = new Quantity<>(1.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> convertedQuantity = volume.convert(VolumeUnit.MILLILITRE);
        Quantity<VolumeUnit> expectedQuantity = new Quantity<>(1000.0,VolumeUnit.MILLILITRE);
        assertEquals(expectedQuantity.getValue(),convertedQuantity.getValue(),0.001);
    }

    @Test
    void backwardCompatibilityWeightPoundEqualsGrams() {
        Quantity<WeightUnit> g1 = new Quantity<>(1, WeightUnit.POUND);
        Quantity<WeightUnit> g2 = new Quantity<>(453.592,WeightUnit.GRAM);
        assertTrue(g1.equals(g2));
    }

    @Test
    void weightKilogramEqualsGrams() {
        Quantity<WeightUnit> kg = new Quantity<>(1.0, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> grams = new Quantity<>(1000.0, WeightUnit.GRAM);
        assertEquals(kg, grams);
    }

    @Test
    void lengthYardsEqualsFeet() {
        Quantity<LengthUnit> yard = new Quantity<>(1.0, LengthUnit.YARDS);
        Quantity<LengthUnit> feet = new Quantity<>(3.0, LengthUnit.FEET);
        assertEquals(yard, feet);
    }

    @Test
    void lengthFeetEqualsInches() {
        Quantity<LengthUnit> feet = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> inches = new Quantity<>(12.0, LengthUnit.INCHES);
        assertEquals(feet, inches);
    }

    @Test
    void backwardCompatibilityWeightKilogramEqualsGrams() {
        Quantity<WeightUnit> g1 = new Quantity<>(1.0, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> g2 = new Quantity<>(1000.0, WeightUnit.GRAM);
        assertTrue(g1.equals(g2));
    }

    @Test
    void addWeightKilogramsAndPounds() {
        Quantity<WeightUnit> kg = new Quantity<>(1.0, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> pound = new Quantity<>(2.20462, WeightUnit.POUND);
        Quantity<WeightUnit> result = kg.add(pound, WeightUnit.KILOGRAM);
        assertEquals(2.0, result.getValue(), 0.01);
    }

    @Test
    void addVolumeLitresAndMillilitres() {
        Quantity<VolumeUnit> volume1 = new Quantity<>(1.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> volume2 = new Quantity<>(1000.0, VolumeUnit.MILLILITRE);
        Quantity<VolumeUnit> result = new Quantity<>(2.0, VolumeUnit.LITRE);
        assertEquals(2.0, result.getValue(), 0.0001);
    }

    @Test
    void weightPoundEqualsGrams() {
        Quantity<WeightUnit> pound = new Quantity<>(1.0, WeightUnit.POUND);
        Quantity<WeightUnit> grams = new Quantity<>(453.592, WeightUnit.GRAM);
        assertEquals(pound, grams);
    }

    @Test
    void addLengthFeetAndInches() {
        Quantity<LengthUnit> feet = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> inches = new Quantity<>(12.0, LengthUnit.INCHES);
        Quantity<LengthUnit> result = feet.add(inches, LengthUnit.FEET);
        assertEquals(2.0, result.getValue(), 0.0001);
    }

    @Test
    void convertLengthFeetToInches() {
        Quantity<LengthUnit> feet = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> result = feet.convert(LengthUnit.INCHES);
        assertEquals(12.0, result.getValue(), 0.0001);
    }

    @Test
    void backwardCompatibilityAddWeightInSameUnit() {
        Quantity<WeightUnit> g1 = new Quantity<>(500, WeightUnit.GRAM);
        Quantity<WeightUnit> g2 = new Quantity<>(500, WeightUnit.GRAM);
        Quantity<WeightUnit> result = g1.add(g2, WeightUnit.GRAM);
        assertEquals(1000, result.getValue(), 0.0001);
    }

    @Test
    void backwardCompatibilityLengthYardsEqualsFeet() {
        Quantity<LengthUnit> f1 = new Quantity<>(1, LengthUnit.YARDS);
        Quantity<LengthUnit> f2 = new Quantity<>(3, LengthUnit.FEET);
        assertTrue(QuantityMeasurementApp.demonstrateEquality(f1,f2));
    }

    @Test
    void backwardCompatibilityConvertLengthFeetToInches() {
        Quantity<LengthUnit> f1 = new Quantity<>(1, LengthUnit.FEET);
        Quantity<LengthUnit> f2 = new Quantity<>(12, LengthUnit.INCHES);
        assertTrue(QuantityMeasurementApp.demonstrateEquality(f1,f2));
    }

    @Test
    void backwardCompatibilityAddLengthInSameUnit() {
        Quantity<LengthUnit> f1 = new Quantity<>(1, LengthUnit.FEET);
        Quantity<LengthUnit> f2 = new Quantity<>(1, LengthUnit.FEET);
        Quantity<LengthUnit> result = f1.add(f2, LengthUnit.FEET);
        assertEquals(2, result.getValue(), 0.0001);
    }

    @Test
    void volumeLiterEqualsMilliliters(){
        Quantity<VolumeUnit> quantity1 = new Quantity<>(1.0,VolumeUnit.LITRE);
        Quantity<VolumeUnit> quantity2 = new Quantity<>(1000.0,VolumeUnit.MILLILITRE);
        assertTrue(QuantityMeasurementApp.demonstrateEquality(quantity1,quantity2));
    }


    @Test
    void convertWeightKilogramsToGrams() {
        Quantity<WeightUnit> kg = new Quantity<>(1.0, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> result = kg.convert(WeightUnit.GRAM);
        assertEquals(1000.0, result.getValue(), 0.0001);
    }

    @Test
    void addWeightKilogramsAndGrams() {
        Quantity<WeightUnit> kg = new Quantity<>(1.0, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> grams = new Quantity<>(1000.0, WeightUnit.GRAM);
        Quantity<WeightUnit> result = kg.add(grams, WeightUnit.KILOGRAM);
        assertEquals(2.0, result.getValue(), 0.0001);
    }

    @Test
    void addWeightTonnesAndKilograms() {
        Quantity<WeightUnit> tonne = new Quantity<>(1.0, WeightUnit.TONNE);
        Quantity<WeightUnit> kg = new Quantity<>(1000.0, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> result = tonne.add(kg, WeightUnit.TONNE);
        assertEquals(2.0, result.getValue(), 0.0001);
    }

    @Test
    void convertLengthYardsToInches() {
        Quantity<LengthUnit> yard = new Quantity<>(1.0, LengthUnit.YARDS);
        Quantity<LengthUnit> result = yard.convert(LengthUnit.INCHES);
        assertEquals(36.0, result.getValue(), 0.0001);
    }
}