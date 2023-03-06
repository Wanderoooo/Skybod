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
        pfl.setHobbsTimeStart(1.1);
        assertFalse(pfl.equals(fl));

        PlaneFlightLog pfl1 = new PlaneFlightLog();
        pfl1.setHobbsTimeStart(1.1);
        pfl1.setHobbsTimeEnd(3.2);
        assertFalse(pfl1.equals(pfl));

        PlaneFlightLog pfl2 = new PlaneFlightLog();
        pfl2.setHobbsTimeStart(1.1);
        pfl2.setHobbsTimeEnd(3.2);
        pfl2.setDepartingAP("PSKF");
        assertFalse(pfl2.equals(pfl1));

        PlaneFlightLog pfl3 = new PlaneFlightLog();
        pfl3.setHobbsTimeStart(1.1);
        pfl3.setHobbsTimeEnd(3.2);
        pfl3.setDepartingAP("PSKF");
        pfl3.setArrivingAP("FSDF");
        assertFalse(pfl3.equals(pfl2));

        PlaneFlightLog pfl4 = new PlaneFlightLog();
        pfl4.setHobbsTimeStart(1.1);
        pfl4.setHobbsTimeEnd(3.2);
        pfl4.setDepartingAP("PSKF");
        pfl4.setArrivingAP("FSDF");
        pfl4.setHoursTillMaint(33);
        assertFalse(pfl4.equals(pfl3));

    }

    @Test
    public void hashCodeTest() {
        assertTrue(fl.equals(piperfl) && piperfl.equals(fl));
        assertTrue(fl.hashCode() == piperfl.hashCode());
    }
}
