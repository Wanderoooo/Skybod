package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class WeightBalanceTest {
    private WeightBalance wb;

    @BeforeEach
    public void runBefore() {
        wb = new WeightBalance();
    }

    @Test
    public void wbConstructorTest() {
        assertEquals(0.0, wb.getFuelGallons());
        assertEquals(0.0, wb.getAircraftWeight());
        assertEquals(0.0, wb.getFuelWeight());
        assertEquals(0.0, wb.getPassengerWeight());
        assertEquals(0.0, wb.getTakeoffWeight());
        assertEquals(0.0, wb.getPilotWeight());
        assertFalse(wb.isWithinLimit());
    }

    @Test
    public void getterSetterTest() {
        wb.setTakeoffWeight(1111.2);
        wb.setFuelGallons(20.5);
        wb.setPilotWeight(150);
        wb.setFuelWeight(70.8);
        wb.setWithinLimit(true);
        wb.setAircraftWeight(900.2);
        wb.setPassengerWeight(200.3);

        assertEquals(1111.2, wb.getTakeoffWeight());
        assertEquals(20.5, wb.getFuelGallons());
        assertEquals(150, wb.getPilotWeight());
        assertEquals(70.8, wb.getFuelWeight());
        assertEquals(900.2, wb.getAircraftWeight());
        assertEquals(200.3, wb.getPassengerWeight());
        assertTrue(wb.isWithinLimit());
    }
}
