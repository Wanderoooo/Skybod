package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
// Testing class for Preflight

public class PreflightTest {
    private Preflight pf;

    @BeforeEach
    public void runBefore() {
        pf = new Preflight();
    }

    @Test
    public void pfConstructorTest() {
        assertNull(pf.getWb());
        assertNull(pf.getDepartAP());
        assertEquals(0.0, pf.getHobbsTimeStart());
        assertFalse(pf.isWBDone());
        assertFalse(pf.isCheckedFireExt());
        assertFalse(pf.isDocOnBoard());
        assertFalse(pf.isFuelEnough());
        assertFalse(pf.isInsuranceValid());
        assertFalse(pf.isPassengerBriefDone());
        assertFalse(pf.isWalkAroundDone());
    }

    @Test
    public void getterSetterTest() {
        pf.setDepartAP("CYVR");
        pf.setHobbsTimeStart(45.0);
        pf.setWBDone(true);
        WeightBalance wb = new WeightBalance();
        pf.setWb(wb);
        pf.setFuelEnough(true);
        pf.setInsuranceValid(true);
        pf.setCheckedFireExt(true);
        pf.setWalkAroundDone(true);
        pf.setPassengerBriefDone(true);
        pf.setDocOnBoard(true);

        assertTrue(pf.isWalkAroundDone());
        assertTrue(pf.isInsuranceValid());
        assertTrue(pf.isFuelEnough());
        assertTrue(pf.isPassengerBriefDone());
        assertTrue(pf.isDocOnBoard());
        assertTrue(pf.isCheckedFireExt());
        assertTrue(pf.isWBDone());
        assertEquals(45.0, pf.getHobbsTimeStart());
        assertEquals("CYVR", pf.getDepartAP());
        assertEquals(wb, pf.getWb());
    }
}
