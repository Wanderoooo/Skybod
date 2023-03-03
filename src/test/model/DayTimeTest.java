package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

// Testing class for DayTime
public class DayTimeTest {
    private DayTime dt1;
    private DayTime dt2;
    private DayTime dt3;

    @BeforeEach
    public void runBefore() {
        dt1 = new DayTime();
        dt2 = new DayTime();
        dt3 = new DayTime();
    }

    @Test
    public void dayTimeConstructorTest() {
        ArrayList<String> arrayList = new ArrayList<>();
        assertEquals(arrayList, dt1.getMonday());
        assertEquals(arrayList, dt1.getTuesday());
        assertEquals(arrayList, dt1.getWednesday());
        assertEquals(arrayList, dt1.getThursday());
        assertEquals(arrayList, dt1.getFriday());
        assertEquals(arrayList, dt1.getSaturday());
        assertEquals(arrayList, dt1.getSunday());
    }

    @Test
    public void addBackTimeGivenDayTest() {
        // String d, String time
        dt1.addBackTimeGivenDay("monday", "1100");
        ArrayList<String> mondayAdd = new ArrayList<>();
        mondayAdd.add("1100");

        assertEquals(mondayAdd, dt1.getMonday());

        dt1.addBackTimeGivenDay("Monday", "1200");
        mondayAdd.add("1200");
        assertEquals(mondayAdd, dt1.getMonday());

        ArrayList<String> mondayAddBefore = new ArrayList();
        mondayAddBefore.add("1000");
        mondayAddBefore.add("1100");
        mondayAddBefore.add("1200");
        dt1.addBackTimeGivenDay("MONDAY", "1000");
        assertEquals(mondayAddBefore, dt1.getMonday());

        ArrayList<String> tuesdayAvails = new ArrayList<>();
        tuesdayAvails.add("0200");
        tuesdayAvails.add("2000");
        dt2.setTuesday(tuesdayAvails);

        ArrayList<String> tuesdayAvails2 = new ArrayList<>();
        tuesdayAvails2.add("0200");
        tuesdayAvails2.add("1000");
        tuesdayAvails2.add("2000");

        dt2.addBackTimeGivenDay("Tuesday", "1000");
        assertEquals(tuesdayAvails2, dt2.getTuesday());

    }

    @Test
    public void addGivenDayTimeTest() {
        dt1.addGivenDayTime("wednesday", "0000", "0100");
        ArrayList<String> w1 = new ArrayList<>();
        w1.add("0000");
        assertEquals(w1, dt1.getWednesday());

        dt2.addGivenDayTime("THURSDAY", "0000", "0400");
        ArrayList<String> t1 = new ArrayList<>();
        t1.add("0000");
        t1.add("0100");
        t1.add("0200");
        t1.add("0300");
        assertEquals(t1, dt2.getThursday());

        dt2.addGivenDayTime("Saturday", "0500", "0700");
        ArrayList<String> s1 = new ArrayList<>();
        s1.add("0500");
        s1.add("0600");
        assertEquals(s1, dt2.getSaturday());

        dt2.addGivenDayTime("saturday", "0800", "0900");
        s1.add("0800");
        assertEquals(s1, dt2.getSaturday());
    }

    @Test
    public void findDayTest() {
        List<String> monday1 = dt1.findDay("monday");
        assertEquals(monday1, dt1.getMonday());
        dt1.addGivenDayTime("thursday", "0000", "0200");
        List<String> thursday1 = dt1.findDay("Thursday");
        assertEquals(thursday1, dt1.getThursday());

        dt2.addGivenDayTime("tuesday", "2000", "2400");
        List<String> tuesday = dt2.findDay("TUESDAY");
        assertEquals(tuesday, dt2.getTuesday());

        List<String> wed = dt1.findDay("wednesday");
        assertEquals(wed, dt1.getWednesday());

        List<String> fri = dt1.findDay("friday");
        assertEquals(fri, dt1.getFriday());

        List<String> sat = dt1.findDay("saturday");
        assertEquals(sat, dt1.getSaturday());

        List<String> sun = dt1.findDay("sunday");
        assertEquals(sun, dt1.getSunday());
    }

    @Test
    public void setDayTest() {
        ArrayList<String> w1 = new ArrayList<>();
        w1.add("0000");
        w1.add("0100");

        dt3.setDay("monday", w1);
        assertEquals(w1, dt3.getMonday());

        ArrayList<String> s1 = new ArrayList<>();
        s1.add("0500");
        s1.add("0600");
        s1.add("0700");

        dt3.setDay("Tuesday", s1);
        assertEquals(s1, dt3.getTuesday());

        dt1.setDay("wednesday", w1);
        assertEquals(w1, dt1.getWednesday());

        dt3.setDay("Thursday", s1);
        assertEquals(s1, dt3.getThursday());

        dt3.setDay("friday", w1);
        assertEquals(w1, dt3.getFriday());

        dt3.setDay("saturday", s1);
        assertEquals(s1, dt3.getSaturday());

        dt3.setDay("sunday", s1);
        assertEquals(s1, dt3.getSunday());
    }

    @Test
    public void equalsTest() {
        assertTrue(dt1.equals(dt1));
        assertFalse(dt1.equals(null));
    }

    @Test
    public void hashCodeTest() {
        assertTrue(dt1.equals(dt2) && dt2.equals(dt1));
        assertTrue(dt1.hashCode() == dt2.hashCode());
    }

}
