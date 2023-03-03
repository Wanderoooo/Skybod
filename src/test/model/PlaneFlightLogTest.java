package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
// Testing class for PlaneFlightLog

public class PlaneFlightLogTest {
    private PlaneFlightLog fl;
    private PlaneFlightLog piperfl;

    @BeforeEach
    public void runBefore() {
        fl = new PlaneFlightLog();
        piperfl = new PlaneFlightLog();
    }

    @Test
    public void flConstructorTest() {
        assertEquals(0.0, fl.getHobbsTimeEnd());
        assertEquals(0.0, fl.getHoursTillMaint());
        assertEquals(0.0, fl.getHobbsTimeStart());
        assertEquals("", fl.getDepartingAP());
        assertEquals("", fl.getArrivingAP());
    }

    @Test
    public void getterSetterTest() {
        piperfl.setArrivingAP("CYVR");
        piperfl.setDepartingAP("CYZY");
        piperfl.setHobbsTimeStart(45.4);
        piperfl.setHobbsTimeEnd(46.0);
        piperfl.setHoursTillMaint(104.8);

        assertEquals("CYVR", piperfl.getArrivingAP());
        assertEquals("CYZY", piperfl.getDepartingAP());
        assertEquals(45.4, piperfl.getHobbsTimeStart());
        assertEquals(46.0, piperfl.getHobbsTimeEnd());
        assertEquals(104.8, piperfl.getHoursTillMaint());
    }

    @Test
    public void equalsTest() {
        Booking bk = new Booking();

        assertFalse(fl.equals(bk));
        assertTrue(fl.equals(piperfl));
        assertTrue(fl.equals(fl));
        assertFalse(fl.equals(null));

        PlaneFlightLog pfl = new PlaneFlightLog();
        pfl.setHobbsTimeEnd(10);
        pfl.setHobbsTimeStart(1);
        pfl.setDepartingAP("DFAA");
        pfl.setArrivingAP("VFSA");

        assertFalse(fl.equals(pfl));
    }

    @Test
    public void hashCodeTest() {
        assertTrue(fl.equals(piperfl) && piperfl.equals(fl));
        assertTrue(fl.hashCode() == piperfl.hashCode());
    }
}
