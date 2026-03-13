package com.apps.quantitymeasurement.entity;

import com.apps.quantitymeasurement.model.QuantityModel;

import java.util.Objects;

public class QuantityMeasurementEntity implements java.io.Serializable{
    private static final long serialVersionUID = 1L;
    public double thisValue;
    public String thisUnit;
    public String thisMeasurementType;
    public double thatValue;
    public String thatUnit;
    public String thatMeasurementType;
    public String operation;
    public double resultValue;
    public String resultUnit;
    public String resultMeasurementType;
    public String resultString;
    public boolean isError;
    public String errorMessage;

    // Base Constructor
    public QuantityMeasurementEntity(QuantityModel<?> thisQuantity ,QuantityModel<?> thatQuantity , String operation){
        if(thisQuantity != null){
            this.thisValue = thisQuantity.getValue();
            this.thisUnit = thisQuantity.getUnit().getUnitName();
            this.thisMeasurementType = thisQuantity.getUnit().getMeasurementType();
        }
        if(thatQuantity != null){
            this.thatValue = thatQuantity.getValue();
            this.thatUnit = thatQuantity.getUnit().getUnitName();
            this.thatMeasurementType = thatQuantity.getUnit().getMeasurementType();
        }
        this.operation = operation;
    }

    // Constructor for Comparison Operation
    public QuantityMeasurementEntity(QuantityModel<?> thisQuantity, QuantityModel<?> thatQuantity, String operation, String resultString) {
        this(thisQuantity,thatQuantity,operation);
        this.resultString = resultString;
        this.isError = false;
    }

    // Constructor for conversion
    public QuantityMeasurementEntity(QuantityModel<?> thisQuantity, String operation, QuantityModel<?> resultQuantity) {
        this(thisQuantity, null, operation);
        this.resultValue = resultQuantity.getValue();
        this.resultUnit = resultQuantity.getUnit().getUnitName();
        this.resultMeasurementType = resultQuantity.getUnit().getMeasurementType();
        this.isError = false;
    }

    // Constructor for Arithmetic operations
    public QuantityMeasurementEntity(QuantityModel<?> thisQuantity, QuantityModel<?> thatQuantity, String operation, QuantityModel<?> resultQuantity) {
        this(thisQuantity, thatQuantity, operation);
        this.resultValue = resultQuantity.getValue();
        this.resultUnit = resultQuantity.getUnit().getUnitName();
        this.resultMeasurementType = resultQuantity.getUnit().getMeasurementType();
        this.isError = false;
    }

    // Constructor for Errors
    public QuantityMeasurementEntity(QuantityModel<?> thisQuantity, QuantityModel<?> thatQuantity, String operation, String errorMessage, boolean isError) {
        this(thisQuantity, thatQuantity, operation);
        this.errorMessage = errorMessage;
        this.isError = isError;
    }

    @Override
    public boolean equals(Object obj){
        if(this == obj) return true;
        if(obj == null) return false;
        if(!(obj instanceof QuantityMeasurementEntity)) return false;
        QuantityMeasurementEntity other = (QuantityMeasurementEntity) obj;

        return Double.compare(this.thisValue, other.thisValue) == 0 &&
                Double.compare(this.thatValue, other.thatValue) == 0 &&
                Double.compare(this.resultValue, other.resultValue) == 0 &&
                Objects.equals(this.thisUnit, other.thisUnit) &&
                Objects.equals(this.thatUnit, other.thatUnit) &&
                Objects.equals(this.operation, other.operation) &&
                Objects.equals(this.resultUnit, other.resultUnit) &&
                Objects.equals(this.resultString, other.resultString) &&
                this.isError == other.isError &&
                Objects.equals(this.errorMessage, other.errorMessage);
    }

    @Override
    public String toString() {
        if (isError) {
            return "Operation: " + operation + " | Error: " + errorMessage;
        }
        if (resultString != null) {
            return "Operation: " + operation + " | Input: " + thisValue + " " + thisUnit + " , " + thatValue + " " + thatUnit + " | Result: " + resultString;
        }
        return "Operation: " + operation + " | Input: " + thisValue + " " + thisUnit + (thatUnit != null ? " , " + thatValue + " " + thatUnit : "") + " | Result: " + resultValue + " " + resultUnit;
    }
}
