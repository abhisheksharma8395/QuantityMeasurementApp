package com.apps.quantitymeasurement;





import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class QuantityMeasurementAppTest {

    // UC-01 Test Cases
    @Test
    public void testFeetEquality(){
        Length feet1 = new Length(5.6, Length.LengthUnit.FEET);
        Length feet2 = new Length(5.6, Length.LengthUnit.FEET);
        assertTrue(feet1.equals(feet2));
    }

    @Test
    public void testInchesEquality(){
        Length inches1 = new Length(7.0, Length.LengthUnit.INCHES);
        Length inches2 = new Length(7.0, Length.LengthUnit.INCHES);
        assertTrue(inches1.equals(inches2));
    }

    @Test
    public void testFeetInchesComparison(){
        Length feet1 = new Length(5.6, Length.LengthUnit.FEET);
        Length inches1 = new Length(67.2, Length.LengthUnit.INCHES);
        assertTrue(feet1.equals(inches1));
    }

    @Test
    public void testFeetInequality(){
        Length feet1 = new Length(5.6, Length.LengthUnit.FEET);
        Length feet2 = new Length(7.2, Length.LengthUnit.FEET);
        assertFalse(feet1.equals(feet2));
    }

    @Test
    public void testInchesInEquality(){
        Length inches1 = new Length(7.0, Length.LengthUnit.INCHES);
        Length inches2 = new Length(7.5, Length.LengthUnit.INCHES);
        assertFalse(inches1.equals(inches2));
    }

    @Test
    public void testCrossUnitInequality(){
        Length feet1 = new Length(5.6, Length.LengthUnit.FEET);
        Length inches1 = new Length(70.2, Length.LengthUnit.INCHES);
        assertFalse(inches1.equals(feet1));
    }

    @Test
    public void testMultipleFeetComparison(){
        Length feet1 = new Length(5.6, Length.LengthUnit.FEET);
        Length feet2 = new Length(5.6, Length.LengthUnit.FEET);
        assertTrue(feet1.equals(feet2));
    }

    @Test
    public void yardEquals36Inches(){
        Length yard1 = new Length(1, Length.LengthUnit.YARDS);
        Length inches1 = new Length(36.0, Length.LengthUnit.INCHES);
        assertTrue(yard1.equals(inches1));
    }

    @Test
    public void centimeterEquals39point3701Inches(){
        Length centimeter = new Length(100.0, Length.LengthUnit.CENTIMETERS);
        Length inches1 = new Length(39.3701, Length.LengthUnit.INCHES);
        assertTrue(centimeter.equals(inches1));
    }

    @Test
    public void threeFeetEqualsOneYard(){
        Length feet1 = new Length(3.0, Length.LengthUnit.FEET);
        Length yard1 = new Length(1.0, Length.LengthUnit.YARDS);
        assertTrue(feet1.equals(yard1));
    }

    @Test
    public void thirtyPoint48cmEqualsOneFoot(){
        Length feet1 = new Length(1.0, Length.LengthUnit.FEET);
        Length centimeter = new Length(30.48, Length.LengthUnit.CENTIMETERS);
        assertTrue(centimeter.equals(feet1));
    }

    @Test
    public void yardNotEqualToInches(){
        Length yard1 = new Length(1, Length.LengthUnit.YARDS);
        Length inches1 = new Length(38.0, Length.LengthUnit.INCHES);
        assertFalse(yard1.equals(inches1));
    }

    @Test
    public void referenceEqualitySameObject(){
        Length yard1 = new Length(1, Length.LengthUnit.YARDS);
        assertTrue(yard1.equals(yard1));
    }

    @Test
    public void equalsReturnsFalseForNull(){
        Length inches1 = new Length(38.0, Length.LengthUnit.INCHES);
        Length yard1 = null;
        assertFalse(inches1.equals(yard1));
    }

    @Test
    public void reflexiveSymmetricAndTransitiveProperty(){
        Length feet1 = new Length(1.0, Length.LengthUnit.FEET);
        // Reflexive
        assertTrue(feet1.equals(feet1));
        Length centimeter = new Length(30.48, Length.LengthUnit.CENTIMETERS);
        // Symmetric
        assertTrue(feet1.equals(centimeter));
        assertTrue(centimeter.equals(feet1));

        Length inches = new Length(12.0, Length.LengthUnit.INCHES);


        // Transitive
        assertTrue(feet1.equals(centimeter));
        assertTrue(centimeter.equals(inches));
        assertTrue(feet1.equals(inches));
    }

    @Test
    public void differentValuesSameUnitNotEqual(){
        Length yard1 = new Length(1.0, Length.LengthUnit.YARDS);
        Length yard2 = new Length(3.0, Length.LengthUnit.YARDS);
        assertFalse(yard1.equals(yard2));
    }

    @Test
    public void crossUnitEqualityDemonstrateMethod(){
        Length yard1 = new Length(1, Length.LengthUnit.YARDS);
        Length inches1 = new Length(36.0, Length.LengthUnit.INCHES);
        assertTrue(yard1.equals(inches1));
    }

    @Test
    public void convertFeetToInches(){
        Length lengthInInches = QuantityMeasurementApp.demonstrateLengthConversion(3.0, Length.LengthUnit.FEET, Length.LengthUnit.INCHES);
        Length expectedLength = new Length(36.0, Length.LengthUnit.INCHES);
        assertTrue(QuantityMeasurementApp.demonstrateLengthEquality(lengthInInches,expectedLength));
    }

    @Test
    public void convertYardsToInchesUsingOverloadedMethod(){
        Length lengthInYards = new Length(3.0, Length.LengthUnit.YARDS);
        Length lengthInInches = QuantityMeasurementApp.demonstrateLengthConversion(lengthInYards, Length.LengthUnit.INCHES);
        Length expectedLength = new Length(72.0, Length.LengthUnit.INCHES);
        assertTrue(QuantityMeasurementApp.demonstrateLengthEquality(lengthInYards,lengthInInches));
    }

    @Test
    public void addFeetAndInches(){
        Length length1 = new Length(1.0, Length.LengthUnit.FEET);
        Length length2 = new Length(12.0, Length.LengthUnit.INCHES);
        Length sumLength = QuantityMeasurementApp.demonstrateLengthAddition(length1,length2);
        Length expectedLength = new Length(2.0, Length.LengthUnit.FEET);
        assertTrue(expectedLength.equals(sumLength));
    }

    @Test
    public void testAddition_ExplicitTargetUnit_Feet(){
        Length length1 = new Length(1.0, Length.LengthUnit.FEET);
        Length length2 = new Length(12.0,Length.LengthUnit.INCHES);
        Length sumLength = QuantityMeasurementApp.demonstrateLengthAddition(length1,length2, Length.LengthUnit.FEET);
        Length expectedLength = new Length(2.0, Length.LengthUnit.FEET);
        assertTrue(expectedLength.equals(sumLength));
    }

    @Test
    public void testAddition_ExplicitTargetUnit_Inches(){
        Length length1 = new Length(1.0, Length.LengthUnit.FEET);
        Length length2 = new Length(12.0,Length.LengthUnit.INCHES);
        Length sumLength = QuantityMeasurementApp.demonstrateLengthAddition(length1,length2, Length.LengthUnit.INCHES);
        Length expectedLength = new Length(24.0, Length.LengthUnit.INCHES);
        assertTrue(expectedLength.equals(sumLength));
    }

    @Test
    public void testAddition_ExplicitTargetUnit_Yards(){
        Length length1 = new Length(1.0, Length.LengthUnit.FEET);
        Length length2 = new Length(12.0,Length.LengthUnit.INCHES);
        Length sumLength = QuantityMeasurementApp.demonstrateLengthAddition(length1,length2, Length.LengthUnit.YARDS);
        double expectedLength = 0.667;
        assertEquals(expectedLength,sumLength.getValue(),0.001);
    }

    @Test
    public void testAddition_ExplicitTargetUnit_Centimeters(){
        Length length1 = new Length(1.0, Length.LengthUnit.INCHES);
        Length length2 = new Length(1.0,Length.LengthUnit.INCHES);
        Length sumLength = QuantityMeasurementApp.demonstrateLengthAddition(length1,length2, Length.LengthUnit.CENTIMETERS);
        Length expectedLength = new Length(5.08, Length.LengthUnit.CENTIMETERS);
        assertTrue(expectedLength.equals(sumLength));
    }

    @Test
    public void testAddition_ExplicitTargetUnit_SameAsFirstOperand(){
        Length length1 = new Length(2.0, Length.LengthUnit.YARDS);
        Length length2 = new Length(3.0,Length.LengthUnit.FEET);
        Length sumLength = QuantityMeasurementApp.demonstrateLengthAddition(length1,length2, Length.LengthUnit.YARDS);
        Length expectedLength = new Length(3.0, Length.LengthUnit.YARDS);
        assertTrue(expectedLength.equals(sumLength));
    }

    @Test
    public void testAddition_ExplicitTargetUnit_SameAsSecondOperand(){
        Length length1 = new Length(2.0, Length.LengthUnit.YARDS);
        Length length2 = new Length(3.0,Length.LengthUnit.FEET);
        Length sumLength = QuantityMeasurementApp.demonstrateLengthAddition(length1,length2, Length.LengthUnit.FEET);
        Length expectedLength = new Length(9.0, Length.LengthUnit.FEET);
        assertTrue(expectedLength.equals(sumLength));
    }

    @Test
    public void testAddition_ExplicitTargetUnit_Commutativity(){
        Length length1 = new Length(1.0, Length.LengthUnit.FEET);
        Length length2 = new Length(12.0,Length.LengthUnit.INCHES);
        Length sumLength1 = QuantityMeasurementApp.demonstrateLengthAddition(length1,length2, Length.LengthUnit.YARDS);

        Length length3 = new Length(12.0, Length.LengthUnit.INCHES);
        Length length4 = new Length(1.0,Length.LengthUnit.FEET);
        Length sumLength2 = QuantityMeasurementApp.demonstrateLengthAddition(length3,length4, Length.LengthUnit.YARDS);
        assertTrue(sumLength1.equals(sumLength2));
    }

    @Test
    public void testAddition_ExplicitTargetUnit_WithZero(){
        Length length1 = new Length(5.0, Length.LengthUnit.FEET);
        Length length2 = new Length(0.0,Length.LengthUnit.INCHES);
        Length sumLength = QuantityMeasurementApp.demonstrateLengthAddition(length1,length2, Length.LengthUnit.YARDS);
        double expectedLength = 1.667;
        assertEquals(expectedLength, sumLength.getValue(),0.001);
    }

    @Test
    public void testAddition_ExplicitTargetUnit_NegativeValues(){
        Length length1 = new Length(5.0, Length.LengthUnit.FEET);
        Length length2 = new Length(-2.0,Length.LengthUnit.FEET);
        Length sumLength = QuantityMeasurementApp.demonstrateLengthAddition(length1,length2, Length.LengthUnit.INCHES);
        Length expectedLength = new Length(36.0, Length.LengthUnit.INCHES);
        assertTrue(expectedLength.equals(sumLength));
    }

    @Test
    public void testAddition_ExplicitTargetUnit_NullTargetUnit(){
        Length length1 = new Length(1.0, Length.LengthUnit.FEET);
        Length length2 = new Length(12.0,Length.LengthUnit.INCHES);
        assertThrows(IllegalArgumentException.class,() -> QuantityMeasurementApp.demonstrateLengthAddition(length1,length2, null));
    }

    @Test
    public void testAddition_ExplicitTargetUnit_LargeToSmallScale() {
        Length length1 = new Length(1000.0, Length.LengthUnit.FEET);
        Length length2 = new Length(500.0, Length.LengthUnit.FEET);
        Length sumLength = QuantityMeasurementApp.demonstrateLengthAddition(length1, length2, Length.LengthUnit.INCHES);
        Length expectedLength = new Length(18000.0, Length.LengthUnit.INCHES);
        assertTrue(expectedLength.equals(sumLength));
    }

    @Test
    public void testAddition_ExplicitTargetUnit_SmallToLargeScale(){
        Length length1 = new Length(12.0, Length.LengthUnit.INCHES);
        Length length2 = new Length(12.0,Length.LengthUnit.INCHES);
        Length sumLength = QuantityMeasurementApp.demonstrateLengthAddition(length1,length2, Length.LengthUnit.YARDS);
        double expectedLength = 0.667;
        assertEquals(expectedLength,sumLength.getValue(),0.001);
    }






}
