package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
// Testing class for Preflight

public class PreflightTest {
    private Preflight pf1;
    private Preflight pf2;

    @BeforeEach
    public void runBefore() {
        pf1 = new Preflight();
        pf2 = new Preflight();
    }

    @Test
    public void pfConstructorTest() {
        assertEquals(0.0, pf1.getWb().getPassengerWeight());
        assertEquals("", pf1.getDepartAP());
        assertEquals(0.0, pf1.getHobbsTimeStart());
        assertFalse(pf1.isWBDone());
        assertFalse(pf1.isCheckedFireExt());
        assertFalse(pf1.isDocOnBoard());
        assertFalse(pf1.isFuelEnough());
        assertFalse(pf1.isInsuranceValid());
        assertFalse(pf1.isPassengerBriefDone());
        assertFalse(pf1.isWalkAroundDone());
    }

    @Test
    public void getterSetterTest() {
        pf1.setDepartAP("CYVR");
        pf1.setHobbsTimeStart(45.0);
        pf1.setWBDone(true);
        WeightBalance wb = new WeightBalance();
        pf1.setWb(wb);
        pf1.setFuelEnough(true);
        pf1.setInsuranceValid(true);
        pf1.setCheckedFireExt(true);
        pf1.setWalkAroundDone(true);
        pf1.setPassengerBriefDone(true);
        pf1.setDocOnBoard(true);

        assertTrue(pf1.isWalkAroundDone());
        assertTrue(pf1.isInsuranceValid());
        assertTrue(pf1.isFuelEnough());
        assertTrue(pf1.isPassengerBriefDone());
        assertTrue(pf1.isDocOnBoard());
        assertTrue(pf1.isCheckedFireExt());
        assertTrue(pf1.isWBDone());
        assertEquals(45.0, pf1.getHobbsTimeStart());
        assertEquals("CYVR", pf1.getDepartAP());
        assertEquals(wb, pf1.getWb());
    }

    @Test
    public void equalsTest() {
        Booking bk = new Booking();

        assertFalse(pf2.equals(bk));
        assertTrue(pf2.equals(pf1));
        assertTrue(pf1.equals(pf1));
        assertFalse(pf1.equals(null));

        Preflight pf = new Preflight();
        pf.setDepartAP("sdfs");
        pf.setHobbsTimeStart(13);
        pf.setInsuranceValid(true);
        pf.setPassengerBriefDone(true);
        pf.setWalkAroundDone(true);
        pf.setCheckedFireExt(true);

        assertFalse(pf2.equals(pf));
    }

    @Test
    public void hashCodeTest() {
        assertTrue(pf1.equals(pf2) && pf2.equals(pf1));
        assertTrue(pf1.hashCode() == pf2.hashCode());
    }
}
