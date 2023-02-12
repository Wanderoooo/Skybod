package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PilotLogTest {
    PilotLog pl1;
    PilotLog pl2;

    @BeforeEach
    public void runBefore() {
        pl1 = new PilotLog();
        pl2 = new PilotLog();
    }

    @Test
    public void pilotLogConstructorTest() {
        assertNull(pl1.getDay());
        assertNull(pl1.getTime());
        assertNull(pl1.getPlaneType());
        assertNull(pl1.getTypeOfPiloting());
        assertNull(pl1.getPlaneCallSign());
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
}
