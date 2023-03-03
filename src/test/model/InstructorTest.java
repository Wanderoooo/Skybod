package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
    }

    @Test
    public void hashCodeTest() {
        assertTrue(i1.equals(i2) && i2.equals(i1));
        assertTrue(i1.hashCode() == i2.hashCode());
    }

}
