package com.apps.quantitymeasurement.repository;

import com.apps.quantitymeasurement.entity.QuantityMeasurementEntity;
import com.apps.quantitymeasurement.util.ConnectionPool;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class QuantityMeasurementDatabaseRepository implements IQuantityMeasurementRepository {

    private static QuantityMeasurementDatabaseRepository instance;

    private QuantityMeasurementDatabaseRepository(){}

    public static QuantityMeasurementDatabaseRepository getInstance(){
        if(instance == null){
            instance = new QuantityMeasurementDatabaseRepository();
        }
        return instance;
    }

    @Override
    public void save(QuantityMeasurementEntity entity) {
        String insertionQuery = "INSERT INTO quantity_measurement_entity (" +
                "this_value, this_unit, this_measurement_type, " +
                "that_value, that_unit, that_measurement_type, " +
                "operation, result_value, result_unit, result_measurement_type, " +
                "result_string, is_error, error_message" +
                ") VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";

        try (Connection conn = ConnectionPool.getConnection();
             PreparedStatement ps = conn.prepareStatement(insertionQuery)) {

            // this quantity
            ps.setDouble(1, entity.thisValue);
            ps.setString(2, entity.thisUnit);
            ps.setString(3, entity.thisMeasurementType);

            // that quantity (can be null)
            if (entity.thatUnit != null) {
                ps.setDouble(4, entity.thatValue);
                ps.setString(5, entity.thatUnit);
                ps.setString(6, entity.thatMeasurementType);
            } else {
                ps.setNull(4, java.sql.Types.DOUBLE);
                ps.setNull(5, java.sql.Types.VARCHAR);
                ps.setNull(6, java.sql.Types.VARCHAR);
            }

            // operation
            ps.setString(7, entity.operation);

            // result
            if (entity.resultUnit != null) {
                ps.setDouble(8, entity.resultValue);
                ps.setString(9, entity.resultUnit);
                ps.setString(10, entity.resultMeasurementType);
            } else {
                ps.setNull(8, java.sql.Types.DOUBLE);
                ps.setNull(9, java.sql.Types.VARCHAR);
                ps.setNull(10, java.sql.Types.VARCHAR);
            }

            // result string
            ps.setString(11, entity.resultString);

            // error
            ps.setBoolean(12, entity.isError);
            ps.setString(13, entity.errorMessage);

            ps.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Error saving to database: " + e.getMessage());
        }
    }

    @Override
    public List<QuantityMeasurementEntity> getAllMeasurements() {
        return List.of();
    }



}
