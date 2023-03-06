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
        WeightBalance wbs = new WeightBalance();
        wbs.setFuelGallons(23894);
        pf.setWb(wbs);
        assertFalse(pf.equals(pf1));

        Preflight pf1 = new Preflight();
        pf1.setWb(wbs);
        pf1.setDocOnBoard(true);
        assertFalse(pf.equals(pf1));

        Preflight pf2 = new Preflight();
        pf2.setWb(wbs);
        pf2.setDocOnBoard(true);
        pf2.setCheckedFireExt(true);
        assertFalse(pf2.equals(pf1));

        Preflight pf3 = new Preflight();
        pf3.setWb(wbs);
        pf3.setDocOnBoard(true);
        pf3.setCheckedFireExt(true);
        pf3.setWalkAroundDone(true);
        assertFalse(pf3.equals(pf2));

        Preflight pf4 = new Preflight();
        pf4.setWb(wbs);
        pf4.setDocOnBoard(true);
        pf4.setCheckedFireExt(true);
        pf4.setWalkAroundDone(true);
        pf4.setFuelEnough(true);
        assertFalse(pf4.equals(pf3));

        Preflight pf5 = new Preflight();
        pf5.setWb(wbs);
        pf5.setDocOnBoard(true);
        pf5.setCheckedFireExt(true);
        pf5.setWalkAroundDone(true);
        pf5.setFuelEnough(true);
        pf5.setWBDone(true);
        assertFalse(pf5.equals(pf4));

        Preflight pf6 = new Preflight();
        pf6.setWb(wbs);
        pf6.setDocOnBoard(true);
        pf6.setCheckedFireExt(true);
        pf6.setWalkAroundDone(true);
        pf6.setFuelEnough(true);
        pf6.setWBDone(true);
        pf6.setPassengerBriefDone(true);
        assertFalse(pf6.equals(pf5));

        Preflight pf7 = new Preflight();
        pf7.setWb(wbs);
        pf7.setDocOnBoard(true);
        pf7.setCheckedFireExt(true);
        pf7.setWalkAroundDone(true);
        pf7.setFuelEnough(true);
        pf7.setWBDone(true);
        pf7.setPassengerBriefDone(true);
        pf7.setInsuranceValid(true);
        assertFalse(pf7.equals(pf6));

        Preflight pf8 = new Preflight();
        pf8.setWb(wbs);
        pf8.setDocOnBoard(true);
        pf8.setCheckedFireExt(true);
        pf8.setWalkAroundDone(true);
        pf8.setFuelEnough(true);
        pf8.setWBDone(true);
        pf8.setPassengerBriefDone(true);
        pf8.setInsuranceValid(true);
        pf8.setHobbsTimeStart(43.2);
        assertFalse(pf8.equals(pf7));

        Preflight pf9 = new Preflight();
        pf9.setWb(wbs);
        pf9.setDocOnBoard(true);
        pf9.setCheckedFireExt(true);
        pf9.setWalkAroundDone(true);
        pf9.setFuelEnough(true);
        pf9.setWBDone(true);
        pf9.setPassengerBriefDone(true);
        pf9.setInsuranceValid(true);
        pf9.setHobbsTimeStart(43.2);
        pf9.setDepartAP("SMMM");
        assertFalse(pf9.equals(pf8));
    }

    @Test
    public void hashCodeTest() {
        assertTrue(pf1.equals(pf2) && pf2.equals(pf1));
        assertTrue(pf1.hashCode() == pf2.hashCode());
    }
}
