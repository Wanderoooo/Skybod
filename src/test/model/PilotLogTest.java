package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
// Testing class for PilotLog

public class PilotLogTest {
    private PilotLog pl1;
    private PilotLog pl2;

    @BeforeEach
    public void runBefore() {
        pl1 = new PilotLog();
        pl2 = new PilotLog();
    }

    @Test
    public void pilotLogConstructorTest() {
        assertEquals("", pl1.getDay());
        assertEquals("", pl1.getTime());
        assertEquals("", pl1.getPlaneType());
        assertEquals("", pl1.getTypeOfPiloting());
        assertEquals("", pl1.getPlaneCallSign());
        assertEquals(0.0, pl1.getFlightTime());
    }

    @Test
    public void getterSetterTest() {
        pl1.setDay("Monday");
        pl1.setPlaneCallSign("C-GUUY");
        pl1.setTypeOfPiloting("solo");
        pl1.setTime("1100");
        pl1.setPlaneType("Cessna-152");
        pl1.setFlightTime(2.0);

        assertEquals("Monday", pl1.getDay());
        assertEquals("C-GUUY", pl1.getPlaneCallSign());
        assertEquals("solo", pl1.getTypeOfPiloting());
        assertEquals(2.0, pl1.getFlightTime());
        assertEquals("1100", pl1.getTime());
        assertEquals("Cessna-152", pl1.getPlaneType());
    }

    @Test
    public void equalsTest() {
        Booking bk = new Booking();

        assertFalse(pl1.equals(bk));
        assertTrue(pl2.equals(pl1));
        assertTrue(pl2.equals(pl2));
        assertFalse(pl2.equals(null));

        PilotLog pl = new PilotLog();
        pl.setPlaneCallSign("GYZZ");
        pl.setTypeOfPiloting("solo");
        pl.setDay("Tuesday");
        pl.setFlightTime(12);
        assertFalse(pl1.equals(pl));
    }

    @Test
    public void hashCodeTest() {
        assertTrue(pl1.equals(pl2) && pl2.equals(pl1));
        assertTrue(pl1.hashCode() == pl2.hashCode());
    }
}
