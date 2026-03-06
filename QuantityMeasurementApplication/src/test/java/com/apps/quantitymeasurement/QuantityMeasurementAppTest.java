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

    @Test
    void testSubtraction_SameUnit_FeetMinusFeet() {
        Quantity<LengthUnit> q1 = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(5.0, LengthUnit.FEET);
        Quantity<LengthUnit> result = q1.subtract(q2);
        assertEquals(new Quantity<>(5.0, LengthUnit.FEET), result);
    }

    @Test
    void testSubtraction_SameUnit_LitreMinusLitre() {
        Quantity<VolumeUnit> q1 = new Quantity<>(10.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> q2 = new Quantity<>(3.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> result = q1.subtract(q2);
        assertEquals(new Quantity<>(7.0, VolumeUnit.LITRE), result);
    }

    @Test
    void testSubtraction_CrossUnit_FeetMinusInches() {
        Quantity<LengthUnit> q1 = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(6.0, LengthUnit.INCHES);
        Quantity<LengthUnit> result = q1.subtract(q2);
        assertEquals(new Quantity<>(9.5, LengthUnit.FEET), result);
    }

    @Test
    void testSubtraction_CrossUnit_InchesMinusFeet() {
        Quantity<LengthUnit> q1 = new Quantity<>(120.0, LengthUnit.INCHES);
        Quantity<LengthUnit> q2 = new Quantity<>(5.0, LengthUnit.FEET);
        Quantity<LengthUnit> result = q1.subtract(q2);
        assertEquals(new Quantity<>(60.0, LengthUnit.INCHES), result);
    }

    @Test
    void testSubtraction_ExplicitTargetUnit_Feet() {
        Quantity<LengthUnit> q1 = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(6.0, LengthUnit.INCHES);
        Quantity<LengthUnit> result = q1.subtract(q2, LengthUnit.FEET);
        assertEquals(new Quantity<>(9.5, LengthUnit.FEET), result);
    }

    @Test
    void testSubtraction_ExplicitTargetUnit_Inches() {
        Quantity<LengthUnit> q1 = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(6.0, LengthUnit.INCHES);
        Quantity<LengthUnit> result = q1.subtract(q2, LengthUnit.INCHES);
        assertEquals(new Quantity<>(114.0, LengthUnit.INCHES), result);
    }

    @Test
    void testSubtraction_ResultingInNegative() {
        Quantity<LengthUnit> result = new Quantity<>(5.0, LengthUnit.FEET).subtract(new Quantity<>(10.0, LengthUnit.FEET));
        assertEquals(new Quantity<>(-5.0, LengthUnit.FEET), result);
    }

    @Test
    void testSubtraction_ResultingInZero() {
        Quantity<LengthUnit> result = new Quantity<>(10.0, LengthUnit.FEET).subtract(new Quantity<>(120.0, LengthUnit.INCHES));
        assertEquals(new Quantity<>(0.0, LengthUnit.FEET), result);
    }

    @Test
    void testSubtraction_WithZeroOperand() {
        Quantity<LengthUnit> result = new Quantity<>(5.0, LengthUnit.FEET).subtract(new Quantity<>(0.0, LengthUnit.INCHES));
        assertEquals(new Quantity<>(5.0, LengthUnit.FEET), result);
    }

    @Test
    void testSubtraction_WithNegativeValues() {
        Quantity<LengthUnit> result = new Quantity<>(5.0, LengthUnit.FEET).subtract(new Quantity<>(-2.0, LengthUnit.FEET));
        assertEquals(new Quantity<>(7.0, LengthUnit.FEET), result);
    }

    @Test
    void testSubtraction_NullOperand() {
        Quantity<LengthUnit> q = new Quantity<>(10.0, LengthUnit.FEET);
        assertThrows(IllegalArgumentException.class, () -> q.subtract(null));
    }

    @Test
    void testSubtraction_NullTargetUnit() {
        Quantity<LengthUnit> q1 = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(5.0, LengthUnit.FEET);
        assertThrows(IllegalArgumentException.class, () -> q1.subtract(q2, null));
    }


    @Test
    void testSubtraction_ChainedOperations() {
        Quantity<LengthUnit> result = new Quantity<>(10.0, LengthUnit.FEET).subtract(new Quantity<>(2.0, LengthUnit.FEET)).subtract(new Quantity<>(1.0, LengthUnit.FEET));
        assertEquals(new Quantity<>(7.0, LengthUnit.FEET), result);
    }

    @Test
    void testDivision_SameUnit_FeetDividedByFeet() {
        double result = new Quantity<>(10.0, LengthUnit.FEET).divide(new Quantity<>(2.0, LengthUnit.FEET));
        assertEquals(5.0, result);
    }

    @Test
    void testDivision_SameUnit_LitreDividedByLitre() {
        double result = new Quantity<>(10.0, VolumeUnit.LITRE).divide(new Quantity<>(5.0, VolumeUnit.LITRE));
        assertEquals(2.0, result);
    }

    @Test
    void testDivision_CrossUnit_FeetDividedByInches() {
        double result = new Quantity<>(24.0, LengthUnit.INCHES).divide(new Quantity<>(2.0, LengthUnit.FEET));
        assertEquals(1.0, result);
    }

    @Test
    void testDivision_CrossUnit_KilogramDividedByGram() {
        double result = new Quantity<>(2.0, WeightUnit.KILOGRAM).divide(new Quantity<>(2000.0, WeightUnit.GRAM));
        assertEquals(1.0, result);
    }

    @Test
    void testDivision_RatioLessThanOne() {
        double result = new Quantity<>(5.0, LengthUnit.FEET).divide(new Quantity<>(10.0, LengthUnit.FEET));
        assertEquals(0.5, result);
    }

    @Test
    void testDivision_RatioEqualToOne() {
        double result = new Quantity<>(10.0, LengthUnit.FEET).divide(new Quantity<>(10.0, LengthUnit.FEET));
        assertEquals(1.0, result);
    }

    @Test
    void testDivision_NonCommutative() {
        double r1 = new Quantity<>(10.0, LengthUnit.FEET).divide(new Quantity<>(5.0, LengthUnit.FEET));
        double r2 = new Quantity<>(5.0, LengthUnit.FEET).divide(new Quantity<>(10.0, LengthUnit.FEET));
        assertNotEquals(r1, r2);
    }

    @Test
    void testDivision_ByZero() {
        assertThrows(ArithmeticException.class, () -> new Quantity<>(10.0, LengthUnit.FEET).divide(new Quantity<>(0.0, LengthUnit.FEET)));
    }

    @Test
    void testDivision_NullOperand() {
        Quantity<LengthUnit> q = new Quantity<>(10.0, LengthUnit.FEET);
        assertThrows(IllegalArgumentException.class, () -> q.divide(null));
    }

    @Test
    void testSubtractionAndDivision_Integration() {
        double result = new Quantity<>(10.0, LengthUnit.FEET).subtract(new Quantity<>(2.0, LengthUnit.FEET)).divide(new Quantity<>(4.0, LengthUnit.FEET));
        assertEquals(2.0, result);
    }

    @Test
    void testSubtractionAddition_Inverse() {
        Quantity<LengthUnit> a = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> b = new Quantity<>(5.0, LengthUnit.FEET);

        Quantity<LengthUnit> result = a.add(b).subtract(b);

        assertEquals(a, result);
    }
}