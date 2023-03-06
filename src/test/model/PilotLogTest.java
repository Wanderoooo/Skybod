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
        pl.setDay("Friday");
        assertFalse(pl1.equals(pl));

        PilotLog pl1 = new PilotLog();
        pl1.setDay("Friday");
        pl1.setTime("2222");
        assertFalse(pl1.equals(pl));

        PilotLog pl2 = new PilotLog();
        pl2.setDay("Friday");
        pl2.setTime("2222");
        pl2.setFlightTime(93);
        assertFalse(pl2.equals(pl1));

        PilotLog pl3 = new PilotLog();
        pl3.setDay("Friday");
        pl3.setTime("2222");
        pl3.setFlightTime(93);
        pl3.setTypeOfPiloting("dual");
        assertFalse(pl3.equals(pl2));

        PilotLog pl4 = new PilotLog();
        pl4.setDay("Friday");
        pl4.setTime("2222");
        pl4.setFlightTime(93);
        pl4.setTypeOfPiloting("dual");
        pl4.setPlaneCallSign("SLLD");
        assertFalse(pl4.equals(pl3));

        PilotLog pl5 = new PilotLog();
        pl5.setDay("Friday");
        pl5.setTime("2222");
        pl5.setFlightTime(93);
        pl5.setTypeOfPiloting("dual");
        pl5.setPlaneCallSign("SLLD");
        pl5.setPlaneType("cessna");
        assertFalse(pl5.equals(pl4));

    }

    @Test
    public void hashCodeTest() {
        assertTrue(pl1.equals(pl2) && pl2.equals(pl1));
        assertTrue(pl1.hashCode() == pl2.hashCode());
    }
}
