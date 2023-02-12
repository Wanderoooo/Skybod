package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

public class InstructorTest {
    Instructor i1;
    Instructor i2;

    @BeforeEach
    public void runBefore() {
        i1 = new Instructor();
    }

    @Test
    public void instructorConstructorTest() {
        assertNull(i1.getAvails());
        assertNull(i1.getName());
        assertEquals(0, i1.getExpYears());
        assertEquals(0, i1.getHourlyRate());
        assertEquals(new HashSet<>() ,i1.getRatings());
        assertNull(i1.getInstrClass());
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

}
