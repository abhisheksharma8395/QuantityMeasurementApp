package com.apps.quantitymeasurement;

public class QuantityMeasurementApp {
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

    public static void main(String[] args) {
        Feet feet1 = new Feet(3.0);
        Feet feet2 = new Feet(3.0);
        System.out.println(feet1.equals(feet2));
    }
}
