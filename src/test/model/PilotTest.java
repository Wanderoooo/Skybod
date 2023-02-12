package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

public class PilotTest {
    Pilot p1;
    Pilot p2;

    @BeforeEach
    public void runBefore() {
        p1 = new Pilot();
        p2 = new Pilot();
    }

    @Test
    public void pilotConstructorTest() {
        assertNull(p1.getName());
        assertEquals(new LinkedList<>(),p1.getBookings());
        assertEquals(new LinkedList<>(), p1.getCancelled());
        assertEquals(new LinkedList<>() ,p1.getToPostFlight());
        assertEquals(new HashSet<>(), p1.getRatings());
        assertEquals(new LinkedList<>(), p1.getCompletedBookings());
        assertEquals(0, p1.getMedNum());
        assertFalse(p1.getStudentStatus());
        assertEquals(new LinkedList<>(), p1.getPl());
    }

    @Test
    public void getterSetterTest() {
        p1.setStudent(true);
        p1.setMedNum(12345);
        p1.setName("Mona");

        assertTrue(p1.getStudentStatus());
        assertEquals(12345, p1.getMedNum());
        assertEquals("Mona", p1.getName());
    }
}
