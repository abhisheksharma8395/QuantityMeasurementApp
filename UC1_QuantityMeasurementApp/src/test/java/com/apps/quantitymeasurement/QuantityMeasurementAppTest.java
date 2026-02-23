package com.apps.quantitymeasurement;





import org.junit.jupiter.api.Test;



import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;


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

}
