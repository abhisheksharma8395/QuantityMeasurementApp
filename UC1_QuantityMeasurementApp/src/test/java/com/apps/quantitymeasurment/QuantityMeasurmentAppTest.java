package com.apps.quantitymeasurment;

import com.apps.quantitymeasurement.QuantityMeasurementApp.Feet;


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;


public class QuantityMeasurmentAppTest {

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
}
