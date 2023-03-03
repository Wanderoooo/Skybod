package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
// Testing class for Booking
public class BookingTest {
    private Booking tb1;
    private Booking tb2;

    @BeforeEach
    public void runBefore() {
        tb1 = new Booking();
        tb2 = new Booking();
    }

    @Test
    public void bookingConstructorTest() {
        assertEquals("", tb1.getDayBooked());
        assertEquals("", tb1.getInstructor().getName());
        assertEquals("", tb1.getPlane().getCallSign());
        assertEquals("", tb1.getPref().getDepartAP());
        assertEquals("", tb1.getTimeBooked());
        assertEquals("", tb1.getReasonCancelled());
        assertEquals("", tb1.getTypeOfLesson());
    }

    @Test
    public void getterSetterTest() {
        String dayBooked1 = "monday";
        String dayBooked2 = "Tuesday";
        Instructor i1 = new Instructor();
        Instructor i2 = new Instructor();
        i2.setName("Jason");
        Plane p1 = new Plane();
        Plane p2 = new Plane();
        p1.setFuelAmount(30);
        Preflight pre1 = new Preflight();
        Preflight pre2 = new Preflight();
        pre2.setDepartAP("CZBB");
        String t1 = "0900";
        String t2 = "2000";
        String r1 = "Sick";
        String type1 = "FLIGHT";
        String type2 = "GROUND";

        tb1.setDayBooked(dayBooked1);
        tb2.setDayBooked(dayBooked2);
        tb1.setInstructor(i1);
        tb2.setInstructor(i2);
        tb1.setPlane(p1);
        tb2.setPlane(p2);
        tb1.setPref(pre1);
        tb2.setPref(pre2);
        tb1.setTimeBooked(t1);
        tb2.setTimeBooked(t2);
        tb1.setReasonCancelled(r1);
        tb1.setTypeOfLesson(type1);
        tb2.setTypeOfLesson(type2);

        assertEquals(dayBooked1, tb1.getDayBooked());
        assertEquals(dayBooked2, tb2.getDayBooked());
        assertEquals(i1, tb1.getInstructor());
        assertEquals(i2, tb2.getInstructor());
        assertEquals(p1, tb1.getPlane());
        assertEquals(p2, tb2.getPlane());
        assertEquals(pre1, tb1.getPref());
        assertEquals(pre2, tb2.getPref());
        assertEquals(t1, tb1.getTimeBooked());
        assertEquals(t2, tb2.getTimeBooked());
        assertEquals(r1, tb1.getReasonCancelled());
        assertEquals("", tb2.getReasonCancelled());
        assertEquals(type1, tb1.getTypeOfLesson());
        assertEquals(type2, tb2.getTypeOfLesson());
    }

    @Test
    public void equalsTest() {
        assertTrue(tb1.equals(tb2));
        assertTrue(tb1.equals(tb1));
        assertFalse(tb1.equals(null));
    }

    @Test
    public void hashCodeTest() {
        assertTrue(tb1.equals(tb2) && tb2.equals(tb1));
        assertTrue(tb1.hashCode() == tb2.hashCode());
    }
}
