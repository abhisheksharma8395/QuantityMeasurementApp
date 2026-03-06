package com.apps.quantitymeasurement;

import com.apps.quantitymeasurement.QuantityMeasurementApp.Feet;
import com.apps.quantitymeasurement.QuantityMeasurementApp.Inches;



import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;


public class QuantityMeasurementAppTest {

    // UC-01 Test
    @Test
    public void testFeetEquality_SameValue(){
        Feet feet1 = new Feet(5.6);
        Feet feet2 = new Feet(5.6);
        assertTrue(feet1.equals(feet2));
    }

    @Test
    public void testFeetEquality_DifferentValue(){
        Feet feet1 = new Feet(5.6);
        Feet feet2 = new Feet(7.8);
        assertFalse(feet1.equals(feet2));
    }

    @Test
    public void testFeetEquality_NullComparison(){
        Feet feet1 = new Feet(5.6);
        Feet feet2 = null;
        assertFalse(feet1.equals(feet2));
    }

    @Test
    public void testFeetEquality_DifferentClass(){
        Feet feet1 = new Feet(5.6);
        Object obj = new Object();
        assertFalse(feet1.equals(obj));
    }

    @Test
    public void testFeetEquality_SameReference(){
        Feet feet1 = new Feet(5.6);
        assertTrue(feet1.equals(feet1));
    }

    // UC - 02 Test Cases
    @Test
    public void testInchesEquality_SameValue(){
        Inches inches1 = new Inches(1.0);
        Inches inches2 = new Inches(1.0);
        assertTrue(inches1.equals(inches2));
    }

    @Test
    public void testInchesEquality_DifferentValue(){
        Inches inches1 = new Inches(9.6);
        Inches inches2 = new Inches(8.9);
        assertFalse(inches1.equals(inches2));
    }

    @Test
    public void testInchesEquality_NullComparison(){
        Inches inches1 = new Inches(9.6);
        Inches inches2 = null;
        assertFalse(inches1.equals(inches2));
    }

    @Test
    public void testInchesEquality_DifferentClass(){
        Inches inches1 = new Inches(9.6);
        Object obj = new Object();
        assertFalse(inches1.equals(obj));
    }

    @Test
    public void testInchesEquality_SameReference(){
        Inches inches1 = new Inches(9.6);
        assertTrue(inches1.equals(inches1));
    }
}
