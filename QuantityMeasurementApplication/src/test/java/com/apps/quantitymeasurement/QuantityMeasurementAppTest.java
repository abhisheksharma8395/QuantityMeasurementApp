package com.apps.quantitymeasurement;

import com.apps.quantitymeasurement.controller.QuantityMeasurementController;
import com.apps.quantitymeasurement.dto.QuantityDTO;
import com.apps.quantitymeasurement.entity.QuantityMeasurementEntity;
import com.apps.quantitymeasurement.exception.QuantityMeasurementException;
import com.apps.quantitymeasurement.model.QuantityModel;
import com.apps.quantitymeasurement.repository.IQuantityMeasurementRepository;
import com.apps.quantitymeasurement.repository.QuantityMeasurementDatabaseRepository;
import com.apps.quantitymeasurement.service.QuantityMeasurementServiceImpl;
import com.apps.quantitymeasurement.unit.LengthUnit;
import com.apps.quantitymeasurement.util.ConnectionPool;

import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class QuantityMeasurementAppTest {

    // DTO TEST
    @Test
    void testDTOCreation() {
        QuantityDTO dto = new QuantityDTO(5, "FEET", "LENGTH");

        assertEquals(5, dto.getValue());
        assertEquals("FEET", dto.getUnit());
        assertEquals("LENGTH", dto.getMeasurementType());
    }

    // MODEL TEST
    @Test
    void testQuantityModel() {
        QuantityModel<LengthUnit> model =
                new QuantityModel<>(10, LengthUnit.FEET);

        assertEquals(10, model.getValue());
        assertEquals(LengthUnit.FEET, model.getUnit());
    }

    // ENTITY TEST Method
    @Test
    void testEntityCreation() {
        QuantityModel<LengthUnit> q1 = new QuantityModel<>(1, LengthUnit.FEET);
        QuantityModel<LengthUnit> result = new QuantityModel<>(2, LengthUnit.FEET);
        QuantityMeasurementEntity entity = new QuantityMeasurementEntity(q1, "CONVERT", result);
        assertEquals("CONVERT", entity.operation);
        assertFalse(entity.isError);
    }

    // SERVICE ADD TEST
    @Test
    void testServiceAdd() throws QuantityMeasurementException {
        IQuantityMeasurementRepository repo = mock(IQuantityMeasurementRepository.class);
        QuantityMeasurementServiceImpl service = new QuantityMeasurementServiceImpl(repo);
        QuantityDTO q1 = new QuantityDTO(1, "FEET", "LENGTH");
        QuantityDTO q2 = new QuantityDTO(12, "INCHES", "LENGTH");
        QuantityDTO result = service.add(q1, q2);
        assertEquals(2.0, result.getValue(), 0.0001);
    }


    @Test
    void testCompareEqual() throws QuantityMeasurementException {
        IQuantityMeasurementRepository repo = mock(IQuantityMeasurementRepository.class);
        QuantityMeasurementServiceImpl service = new QuantityMeasurementServiceImpl(repo);
        QuantityDTO q1 = new QuantityDTO(1, "FEET", "LENGTH");
        QuantityDTO q2 = new QuantityDTO(12, "INCHES", "LENGTH");
        boolean result = service.compare(q1, q2);
        assertTrue(result);
    }

    // CONTROLLER TEST
    @Test
    void testControllerCallsService() throws QuantityMeasurementException {
        var service = mock(com.apps.quantitymeasurement.service.IQuantityMeasurementService.class);
        QuantityMeasurementController controller = new QuantityMeasurementController(service);
        QuantityDTO dto = new QuantityDTO(1, "FEET", "LENGTH");
        controller.performComparison(dto, dto);
        verify(service).compare(dto, dto);
    }

    // DATABASE TEST
    @Test
    void testDatabaseSave() {
        QuantityMeasurementDatabaseRepository repo = QuantityMeasurementDatabaseRepository.getInstance();
        QuantityModel<LengthUnit> q1 = new QuantityModel<>(1, LengthUnit.FEET);
        QuantityModel<LengthUnit> result = new QuantityModel<>(2, LengthUnit.FEET);
        QuantityMeasurementEntity entity = new QuantityMeasurementEntity(q1, "CONVERT", result);
        repo.save(entity);
        assertTrue(true);
    }

    // CONNECTION TEST
    @Test
    void testConnectionPool() throws Exception {
        Connection conn = ConnectionPool.getConnection();
        assertNotNull(conn);
    }

    // DIVIDE TEST
    @Test
    void testDivide() throws QuantityMeasurementException {
        IQuantityMeasurementRepository repo = mock(IQuantityMeasurementRepository.class);
        QuantityMeasurementServiceImpl service = new QuantityMeasurementServiceImpl(repo);
        QuantityDTO q1 = new QuantityDTO(10, "FEET", "LENGTH");
        QuantityDTO q2 = new QuantityDTO(5, "FEET", "LENGTH");
        double result = service.divide(q1, q2);
        assertEquals(2.0, result, 0.0001);
    }
}