package com.apps.quantitymeasurement;





import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class QuantityMeasurementAppTest {

    // UC-01 Test Cases
    @Test
    public void testFeetEquality(){
        Length feet1 = new Length(5.6, LengthUnit.FEET);
        Length feet2 = new Length(5.6, LengthUnit.FEET);
        assertTrue(feet1.equals(feet2));
    }

    @Test
    public void testInchesEquality(){
        Length inches1 = new Length(7.0, LengthUnit.INCHES);
        Length inches2 = new Length(7.0, LengthUnit.INCHES);
        assertTrue(inches1.equals(inches2));
    }

    @Test
    public void testFeetInchesComparison(){
        Length feet1 = new Length(5.6, LengthUnit.FEET);
        Length inches1 = new Length(67.2, LengthUnit.INCHES);
        assertTrue(feet1.equals(inches1));
    }

    @Test
    public void testFeetInequality(){
        Length feet1 = new Length(5.6, LengthUnit.FEET);
        Length feet2 = new Length(7.2, LengthUnit.FEET);
        assertFalse(feet1.equals(feet2));
    }

    @Test
    public void testInchesInEquality(){
        Length inches1 = new Length(7.0, LengthUnit.INCHES);
        Length inches2 = new Length(7.5, LengthUnit.INCHES);
        assertFalse(inches1.equals(inches2));
    }

    @Test
    public void testCrossUnitInequality(){
        Length feet1 = new Length(5.6, LengthUnit.FEET);
        Length inches1 = new Length(70.2, LengthUnit.INCHES);
        assertFalse(inches1.equals(feet1));
    }

    @Test
    public void testMultipleFeetComparison(){
        Length feet1 = new Length(5.6, LengthUnit.FEET);
        Length feet2 = new Length(5.6, LengthUnit.FEET);
        assertTrue(feet1.equals(feet2));
    }

    @Test
    public void yardEquals36Inches(){
        Length yard1 = new Length(1, LengthUnit.YARDS);
        Length inches1 = new Length(36.0, LengthUnit.INCHES);
        assertTrue(yard1.equals(inches1));
    }

    @Test
    public void centimeterEquals39point3701Inches(){
        Length centimeter = new Length(100.0, LengthUnit.CENTIMETERS);
        Length inches1 = new Length(39.3701, LengthUnit.INCHES);
        assertTrue(centimeter.equals(inches1));
    }

    @Test
    public void threeFeetEqualsOneYard(){
        Length feet1 = new Length(3.0, LengthUnit.FEET);
        Length yard1 = new Length(1.0, LengthUnit.YARDS);
        assertTrue(feet1.equals(yard1));
    }

    @Test
    public void thirtyPoint48cmEqualsOneFoot(){
        Length feet1 = new Length(1.0, LengthUnit.FEET);
        Length centimeter = new Length(30.48, LengthUnit.CENTIMETERS);
        assertTrue(centimeter.equals(feet1));
    }

    @Test
    public void yardNotEqualToInches(){
        Length yard1 = new Length(1, LengthUnit.YARDS);
        Length inches1 = new Length(38.0, LengthUnit.INCHES);
        assertFalse(yard1.equals(inches1));
    }

    @Test
    public void referenceEqualitySameObject(){
        Length yard1 = new Length(1, LengthUnit.YARDS);
        assertTrue(yard1.equals(yard1));
    }

    @Test
    public void equalsReturnsFalseForNull(){
        Length inches1 = new Length(38.0, LengthUnit.INCHES);
        Length yard1 = null;
        assertFalse(inches1.equals(yard1));
    }

    @Test
    public void reflexiveSymmetricAndTransitiveProperty(){
        Length feet1 = new Length(1.0, LengthUnit.FEET);
        // Reflexive
        assertTrue(feet1.equals(feet1));
        Length centimeter = new Length(30.48, LengthUnit.CENTIMETERS);
        // Symmetric
        assertTrue(feet1.equals(centimeter));
        assertTrue(centimeter.equals(feet1));

        Length inches = new Length(12.0, LengthUnit.INCHES);


        // Transitive
        assertTrue(feet1.equals(centimeter));
        assertTrue(centimeter.equals(inches));
        assertTrue(feet1.equals(inches));
    }

    @Test
    public void differentValuesSameUnitNotEqual(){
        Length yard1 = new Length(1.0, LengthUnit.YARDS);
        Length yard2 = new Length(3.0, LengthUnit.YARDS);
        assertFalse(yard1.equals(yard2));
    }

    @Test
    public void crossUnitEqualityDemonstrateMethod(){
        Length yard1 = new Length(1, LengthUnit.YARDS);
        Length inches1 = new Length(36.0, LengthUnit.INCHES);
        assertTrue(yard1.equals(inches1));
    }

    @Test
    public void convertFeetToInches(){
        Length lengthInInches = QuantityMeasurementApp.demonstrateLengthConversion(3.0, LengthUnit.FEET, LengthUnit.INCHES);
        Length expectedLength = new Length(36.0, LengthUnit.INCHES);
        assertTrue(QuantityMeasurementApp.demonstrateLengthEquality(lengthInInches,expectedLength));
    }

    @Test
    public void convertYardsToInchesUsingOverloadedMethod(){
        Length lengthInYards = new Length(3.0, LengthUnit.YARDS);
        Length lengthInInches = QuantityMeasurementApp.demonstrateLengthConversion(lengthInYards, LengthUnit.INCHES);
        Length expectedLength = new Length(72.0, LengthUnit.INCHES);
        assertTrue(QuantityMeasurementApp.demonstrateLengthEquality(lengthInYards,lengthInInches));
    }

    @Test
    public void addFeetAndInches(){
        Length length1 = new Length(1.0, LengthUnit.FEET);
        Length length2 = new Length(12.0, LengthUnit.INCHES);
        Length sumLength = QuantityMeasurementApp.demonstrateLengthAddition(length1,length2);
        Length expectedLength = new Length(2.0, LengthUnit.FEET);
        assertTrue(expectedLength.equals(sumLength));
    }

    @Test
    public void testAddition_ExplicitTargetUnit_Feet(){
        Length length1 = new Length(1.0, LengthUnit.FEET);
        Length length2 = new Length(12.0, LengthUnit.INCHES);
        Length sumLength = QuantityMeasurementApp.demonstrateLengthAddition(length1,length2, LengthUnit.FEET);
        Length expectedLength = new Length(2.0, LengthUnit.FEET);
        assertTrue(expectedLength.equals(sumLength));
    }

    @Test
    public void testAddition_ExplicitTargetUnit_Inches(){
        Length length1 = new Length(1.0, LengthUnit.FEET);
        Length length2 = new Length(12.0, LengthUnit.INCHES);
        Length sumLength = QuantityMeasurementApp.demonstrateLengthAddition(length1,length2, LengthUnit.INCHES);
        Length expectedLength = new Length(24.0, LengthUnit.INCHES);
        assertTrue(expectedLength.equals(sumLength));
    }

    @Test
    public void testAddition_ExplicitTargetUnit_Yards(){
        Length length1 = new Length(1.0, LengthUnit.FEET);
        Length length2 = new Length(12.0,LengthUnit.INCHES);
        Length sumLength = QuantityMeasurementApp.demonstrateLengthAddition(length1,length2, LengthUnit.YARDS);
        double expectedLength = 0.667;
        assertEquals(expectedLength,sumLength.getValue(),0.001);
    }

    @Test
    public void testAddition_ExplicitTargetUnit_Centimeters(){
        Length length1 = new Length(1.0, LengthUnit.INCHES);
        Length length2 = new Length(1.0, LengthUnit.INCHES);
        Length sumLength = QuantityMeasurementApp.demonstrateLengthAddition(length1,length2, LengthUnit.CENTIMETERS);
        Length expectedLength = new Length(5.08, LengthUnit.CENTIMETERS);
        assertTrue(expectedLength.equals(sumLength));
    }

    @Test
    public void testAddition_ExplicitTargetUnit_SameAsFirstOperand(){
        Length length1 = new Length(2.0, LengthUnit.YARDS);
        Length length2 = new Length(3.0, LengthUnit.FEET);
        Length sumLength = QuantityMeasurementApp.demonstrateLengthAddition(length1,length2, LengthUnit.YARDS);
        Length expectedLength = new Length(3.0, LengthUnit.YARDS);
        assertTrue(expectedLength.equals(sumLength));
    }

    @Test
    public void testAddition_ExplicitTargetUnit_SameAsSecondOperand(){
        Length length1 = new Length(2.0, LengthUnit.YARDS);
        Length length2 = new Length(3.0, LengthUnit.FEET);
        Length sumLength = QuantityMeasurementApp.demonstrateLengthAddition(length1,length2, LengthUnit.FEET);
        Length expectedLength = new Length(9.0, LengthUnit.FEET);
        assertTrue(expectedLength.equals(sumLength));
    }

    @Test
    public void testAddition_ExplicitTargetUnit_Commutativity(){
        Length length1 = new Length(1.0, LengthUnit.FEET);
        Length length2 = new Length(12.0, LengthUnit.INCHES);
        Length sumLength1 = QuantityMeasurementApp.demonstrateLengthAddition(length1,length2, LengthUnit.YARDS);

        Length length3 = new Length(12.0, LengthUnit.INCHES);
        Length length4 = new Length(1.0, LengthUnit.FEET);
        Length sumLength2 = QuantityMeasurementApp.demonstrateLengthAddition(length3,length4, LengthUnit.YARDS);
        assertTrue(sumLength1.equals(sumLength2));
    }

    @Test
    public void testAddition_ExplicitTargetUnit_WithZero(){
        Length length1 = new Length(5.0, LengthUnit.FEET);
        Length length2 = new Length(0.0, LengthUnit.INCHES);
        Length sumLength = QuantityMeasurementApp.demonstrateLengthAddition(length1,length2, LengthUnit.YARDS);
        double expectedLength = 1.667;
        assertEquals(expectedLength, sumLength.getValue(),0.001);
    }

    @Test
    public void testAddition_ExplicitTargetUnit_NegativeValues(){
        Length length1 = new Length(5.0, LengthUnit.FEET);
        Length length2 = new Length(-2.0, LengthUnit.FEET);
        Length sumLength = QuantityMeasurementApp.demonstrateLengthAddition(length1,length2, LengthUnit.INCHES);
        Length expectedLength = new Length(36.0, LengthUnit.INCHES);
        assertTrue(expectedLength.equals(sumLength));
    }

    @Test
    public void testAddition_ExplicitTargetUnit_NullTargetUnit(){
        Length length1 = new Length(1.0, LengthUnit.FEET);
        Length length2 = new Length(12.0, LengthUnit.INCHES);
        assertThrows(IllegalArgumentException.class,() -> QuantityMeasurementApp.demonstrateLengthAddition(length1,length2, null));
    }

    @Test
    public void testAddition_ExplicitTargetUnit_LargeToSmallScale() {
        Length length1 = new Length(1000.0, LengthUnit.FEET);
        Length length2 = new Length(500.0, LengthUnit.FEET);
        Length sumLength = QuantityMeasurementApp.demonstrateLengthAddition(length1, length2, LengthUnit.INCHES);
        Length expectedLength = new Length(18000.0, LengthUnit.INCHES);
        assertTrue(expectedLength.equals(sumLength));
    }

    @Test
    public void testAddition_ExplicitTargetUnit_SmallToLargeScale(){
        Length length1 = new Length(12.0, LengthUnit.INCHES);
        Length length2 = new Length(12.0, LengthUnit.INCHES);
        Length sumLength = QuantityMeasurementApp.demonstrateLengthAddition(length1,length2, LengthUnit.YARDS);
        double expectedLength = 0.667;
        assertEquals(expectedLength,sumLength.getValue(),0.001);
    }

    @Test
    public void testLengthUnitEnum_FeetConstant(){
        assertEquals(1.0,LengthUnit.FEET.getConversionFactor(),0.001);
    }

    @Test
    public void testLengthUnitEnum_InchesConstant(){
        assertEquals(0.0833,LengthUnit.INCHES.getConversionFactor(),0.001);
    }

    @Test
    public void testLengthUnitEnum_YardsConstant(){
        assertEquals(3.0,LengthUnit.YARDS.getConversionFactor(),0.001);
    }

    @Test
    public void testLengthUnitEnum_CentimetersConstant(){
        assertEquals(0.0328,LengthUnit.CENTIMETERS.getConversionFactor(),0.001);
    }

    @Test
    public void testLengthUnitEnum_FeetToFeet(){
        assertEquals(5.0,LengthUnit.FEET.convertToBaseUnit(5.0),0.001);
    }

    @Test
    public void testConvertToBaseUnit_InchesToFeet(){
        assertEquals(1.0,LengthUnit.INCHES.convertToBaseUnit(12.0),0.001);
    }

    @Test
    public void testConvertToBaseUnit_YardsToFeet(){
        assertEquals(3.0,LengthUnit.YARDS.convertToBaseUnit(1.0),0.001);
    }

    @Test
    public void testConvertToBaseUnit_CentimetersToFeet(){
        assertEquals(1.0,LengthUnit.CENTIMETERS.convertToBaseUnit(30.48),0.001);
    }

    @Test
    public void testConvertFromBaseUnit_FeetToFeet(){
        assertEquals(2.0,LengthUnit.FEET.convertFromBaseUnit(2.0),0.001);
    }

    @Test
    public void testConvertFromBaseUnit_FeetToInches(){
        assertEquals(12.0,LengthUnit.INCHES.convertFromBaseUnit(1.0),0.001);
    }

    @Test
    public void testConvertFromBaseUnit_FeetToYards(){
        assertEquals(1.0,LengthUnit.YARDS.convertFromBaseUnit(3.0),0.001);
    }

    @Test
    public void testConvertToBaseUnit_FeetToCentimeters(){
        assertEquals(30.48,LengthUnit.CENTIMETERS.convertFromBaseUnit(1.0),0.001);
    }

    @Test
    public void testQuantityLengthRefactored_Equality(){
        assertTrue(new Length(1.0,LengthUnit.FEET).equals(new Length(12.0,LengthUnit.INCHES)));
    }

    @Test
    public void testQuantityLengthRefactored_ConvertTo(){
        assertEquals(12.0,new Length(1.0,LengthUnit.FEET).convertTo(LengthUnit.INCHES).getValue(),0.001);
    }

    @Test
    public void testQuantityLengthRefactored_Add(){
        Length length1 = new Length(1.0,LengthUnit.FEET);
        Length length2 = new Length(12.0,LengthUnit.INCHES);
        Length sumLength = QuantityMeasurementApp.demonstrateLengthAddition(length1,length2);
        Length expectedLength = new Length(2.0,LengthUnit.FEET);
        assertTrue(expectedLength.equals(sumLength));
    }

    @Test
    public void testQuantityLengthRefactored_AddWithTargetUnit(){
        Length length1 = new Length(1.0,LengthUnit.FEET);
        Length length2 = new Length(12.0,LengthUnit.INCHES);
        Length sumLength = QuantityMeasurementApp.demonstrateLengthAddition(length1,length2,LengthUnit.YARDS);
        assertEquals(0.667,sumLength.getValue(),0.001);
    }

    @Test
    public void testQuantityLengthRefactored_NullUnit(){
        assertThrows(IllegalArgumentException.class , () -> new Length(1.0,null));
    }

    @Test
    public void testQuantityLengthRefactored_InvalidValue(){
        assertThrows(IllegalArgumentException.class , () -> new Length(Double.NaN,LengthUnit.FEET));
    }






}
