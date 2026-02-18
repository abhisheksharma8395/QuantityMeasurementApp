package com.apps.quantitymeasurement;

public class QuantityMeasurementApp {

    // UC-01
    public static class Feet {
        private final double value;                               // Stores the feets

        // Constructor of Feet class
        public Feet(double value) {
            this.value = value;
        }

        // Getter method -> To get the value of Feet
        public double getValue() {
            return value;
        }

        @Override
        public boolean equals(Object obj) {
            if(obj == null) return false;
            if(!(obj instanceof Feet)) return false;
            if(obj == this) return true;
            return Double.compare(this.value,((Feet) obj).value) == 0;
        }
    }

    // UC-02
    public static class Inches{

        private final double value;                               // Stores the feets

        // Constructor of Feet class
        public Inches(double value) {
            this.value = value;
        }

        // Getter method -> To get the value of Feet
        public double getValue() {
            return value;
        }

        @Override
        public boolean equals(Object obj) {
            if(obj == null) return false;
            if(!(obj instanceof Inches)) return false;
            if(obj == this) return true;
            return Double.compare(this.value,((Inches) obj).value) == 0;
        }
    }

    public static void demonstrateFeetEquality(){
        Feet feet1 = new Feet(1.0);
        Feet feet2 = new Feet(1.0);
        System.out.println(feet1.equals(feet2));
    }

    public static void demonstrateInchesEquality(){
        Inches inches1 = new Inches(1.0);
        Inches inches2 = new Inches(1.0);
        System.out.println(inches1.equals(inches2));
    }

    public static void main(String[] args) {
        demonstrateFeetEquality();
        demonstrateInchesEquality();
    }
}
