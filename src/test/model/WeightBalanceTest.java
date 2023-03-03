package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
// Testing class for WeightBalance

public class WeightBalanceTest {
    private WeightBalance wb1;
    private WeightBalance wb2;


    @BeforeEach
    public void runBefore() {
        wb1 = new WeightBalance();
        wb2 = new WeightBalance();
    }

    @Test
    public void wbConstructorTest() {
        assertEquals(0.0, wb1.getFuelGallons());
        assertEquals(0.0, wb1.getAircraftWeight());
        assertEquals(0.0, wb1.getFuelWeight());
        assertEquals(0.0, wb1.getPassengerWeight());
        assertEquals(0.0, wb1.getTakeoffWeight());
        assertEquals(0.0, wb1.getPilotWeight());
        assertFalse(wb1.isWithinLimit());
    }

    @Test
    public void getterSetterEqualTest() {
        wb1.setTakeoffWeight(1111.2);
        wb1.setFuelGallons(20.5);
        wb1.setPilotWeight(150);
        wb1.setFuelWeight(70.8);
        wb1.setWithinLimit(true);
        wb1.setAircraftWeight(900.2);
        wb1.setPassengerWeight(200.3);

        assertEquals(1111.2, wb1.getTakeoffWeight());
        assertEquals(20.5, wb1.getFuelGallons());
        assertEquals(150, wb1.getPilotWeight());
        assertEquals(70.8, wb1.getFuelWeight());
        assertEquals(900.2, wb1.getAircraftWeight());
        assertEquals(200.3, wb1.getPassengerWeight());
        assertTrue(wb1.isWithinLimit());
    }

    @Test
    public void equalsTest() {
        Booking bk = new Booking();

        assertFalse(wb1.equals(bk));
        assertTrue(wb2.equals(wb1));
        assertTrue(wb1.equals(wb1));
        assertFalse(wb1.equals(null));
    }

    @Test
    public void hashCodeTest() {
        assertTrue(wb1.equals(wb2) && wb2.equals(wb1));
        assertTrue(wb1.hashCode() == wb2.hashCode());
    }
}
