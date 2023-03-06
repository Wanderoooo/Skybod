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

        WeightBalance wb = new WeightBalance();
        wb.setAircraftWeight(77);
        assertFalse(wb1.equals(wb));

        WeightBalance wb2 = new WeightBalance();
        wb2.setAircraftWeight(77);
        wb2.setFuelGallons(282);
        assertFalse(wb2.equals(wb));

        WeightBalance wb3 = new WeightBalance();
        wb3.setAircraftWeight(77);
        wb3.setFuelGallons(282);
        wb3.setFuelWeight(88);
        assertFalse(wb3.equals(wb2));

        WeightBalance wb4 = new WeightBalance();
        wb4.setAircraftWeight(77);
        wb4.setFuelGallons(282);
        wb4.setFuelWeight(88);
        wb4.setPilotWeight(4.44);
        assertFalse(wb4.equals(wb3));

        WeightBalance wb5 = new WeightBalance();
        wb5.setAircraftWeight(77);
        wb5.setFuelGallons(282);
        wb5.setFuelWeight(88);
        wb5.setPilotWeight(4.44);
        wb5.setPassengerWeight(90);
        assertFalse(wb5.equals(wb4));

        WeightBalance wb6 = new WeightBalance();
        wb6.setAircraftWeight(77);
        wb6.setFuelGallons(282);
        wb6.setFuelWeight(88);
        wb6.setPilotWeight(4.44);
        wb6.setPassengerWeight(90);
        wb6.setTakeoffWeight(5.65);
        assertFalse(wb6.equals(wb5));

        WeightBalance wb7 = new WeightBalance();
        wb7.setAircraftWeight(77);
        wb7.setFuelGallons(282);
        wb7.setFuelWeight(88);
        wb7.setPilotWeight(4.44);
        wb7.setPassengerWeight(90);
        wb7.setTakeoffWeight(5.65);
        wb7.setWithinLimit(true);
        assertFalse(wb7.equals(wb6));
    }

    @Test
    public void hashCodeTest() {
        assertTrue(wb1.equals(wb2) && wb2.equals(wb1));
        assertTrue(wb1.hashCode() == wb2.hashCode());
    }
}
