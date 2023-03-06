package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;
// Testing class for Instructor

public class InstructorTest {
    private Instructor i1;
    private Instructor i2;

    @BeforeEach
    public void runBefore() {
        i1 = new Instructor();
        i2 = new Instructor();
    }

    @Test
    public void instructorConstructorTest() {
        assertEquals(0, i1.getAvails().getSunday().size());
        assertEquals("", i1.getName());
        assertEquals(0, i1.getExpYears());
        assertEquals(0, i1.getHourlyRate());
        assertEquals(new HashSet<>() ,i1.getRatings());
        assertEquals("", i1.getInstrClass());
    }

    @Test
    public void getterSetterTest() {
        DayTime dt = new DayTime();
        dt.addGivenDayTime("monday", "0000", "1200");
        HashSet<String> ratings = new HashSet<>();
        ratings.add("Float");

        i1.setName("Johanne");
        i1.setAvails(dt);
        i1.setRatings(ratings);
        i1.setHourlyRate(120);
        i1.setExpYears(3);
        i1.setInstrClass("CFII");

        assertEquals("Johanne", i1.getName());
        assertEquals(dt, i1.getAvails());
        assertEquals(ratings, i1.getRatings());
        assertEquals(120, i1.getHourlyRate());
        assertEquals(3, i1.getExpYears());
        assertEquals("CFII", i1.getInstrClass());
    }

    @Test
    public void equalsTest() {
        Booking bk = new Booking();

        assertFalse(i1.equals(bk));
        assertTrue(i2.equals(i1));
        assertTrue(i1.equals(i1));
        assertFalse(i1.equals(null));

        Instructor i2 = new Instructor();
        i2.setName("On");
        assertFalse(i2.equals(i1));

        Instructor i3 = new Instructor();
        HashSet<String> hs = new HashSet<>();
        hs.add("Float");
        i3.setName("On");
        i3.setRatings(hs);
        assertFalse(i3.equals(i2));

        Instructor i4 = new Instructor();
        DayTime dt = new DayTime();
        ArrayList<String> d = new ArrayList<>();
        d.add("1900");
        dt.setDay("monday", d);
        i4.setName("On");
        i4.setRatings(hs);
        i4.setAvails(dt);
        assertFalse(i4.equals(i3));

        Instructor i5 = new Instructor();
        i5.setName("On");
        i5.setRatings(hs);
        i5.setAvails(dt);
        i5.setHourlyRate(88);
        assertFalse(i5.equals(i4));

        Instructor i6 = new Instructor();
        i6.setName("On");
        i6.setRatings(hs);
        i6.setAvails(dt);
        i6.setHourlyRate(88);
        i6.setExpYears(7);
        assertFalse(i6.equals(i5));

    }

    @Test
    public void hashCodeTest() {
        assertTrue(i1.equals(i2) && i2.equals(i1));
        assertTrue(i1.hashCode() == i2.hashCode());
    }

}
