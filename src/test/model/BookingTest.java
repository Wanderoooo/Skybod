package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.print.Book;

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
        Insurance ins = new Insurance();

        assertFalse(tb1.equals(ins));
        assertTrue(tb1.equals(tb2));
        assertTrue(tb1.equals(tb1));
        assertFalse(tb1.equals(null));

        Booking bk1 = new Booking();
        Plane pl1 = new Plane();
        pl1.setHourlyFuelRate(30);
        bk1.setPlane(pl1);
        assertFalse(bk1.equals(tb1));

        Booking bk2 = new Booking();
        Instructor i1 = new Instructor();
        i1.setName("Jonny");
        bk2.setInstructor(i1);
        bk2.setPlane(pl1);

        assertFalse(bk2.equals(bk1));

        Booking bk3 = new Booking();
        bk3.setDayBooked("Sunday");
        bk3.setInstructor(i1);
        bk3.setPlane(pl1);

        assertFalse(bk3.equals(bk2));

        Booking bk4 = new Booking();
        bk4.setTimeBooked("2300");
        bk4.setDayBooked("Sunday");
        bk4.setInstructor(i1);
        bk4.setPlane(pl1);

        assertFalse(bk4.equals(bk3));

        Booking bk5 = new Booking();
        Preflight pf = new Preflight();
        pf.setCheckedFireExt(true);
        bk5.setPref(pf);
        bk5.setTimeBooked("2300");
        bk5.setDayBooked("Sunday");
        bk5.setInstructor(i1);
        bk5.setPlane(pl1);

        assertFalse(bk5.equals(bk4));

        Booking bk6 = new Booking();
        bk6.setReasonCancelled("sick");
        bk6.setPref(pf);
        bk6.setTimeBooked("2300");
        bk6.setDayBooked("Sunday");
        bk6.setInstructor(i1);
        bk6.setPlane(pl1);

        assertFalse(bk6.equals(bk5));

        Booking bk7 = new Booking();
        bk7.setTypeOfLesson("GROUND");
        bk7.setReasonCancelled("sick");
        bk7.setPref(pf);
        bk7.setTimeBooked("2300");
        bk7.setDayBooked("Sunday");
        bk7.setInstructor(i1);
        bk7.setPlane(pl1);
        assertFalse(bk7.equals(bk6));

    }

    @Test
    public void hashCodeTest() {
        assertTrue(tb1.equals(tb2) && tb2.equals(tb1));
        assertTrue(tb1.hashCode() == tb2.hashCode());
    }
}
