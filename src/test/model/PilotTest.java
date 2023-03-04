package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;
// Testing class for Pilot

public class PilotTest {
    private Pilot p1;
    private Pilot p2;

    @BeforeEach
    public void runBefore() {
        p1 = new Pilot();
        p2 = new Pilot();
    }

    @Test
    public void pilotConstructorTest() {
        assertEquals("", p1.getName());
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
        HashSet<String> ratings = new HashSet<>();
        ratings.add("Float");
        ratings.add("Multi");
        ratings.add("VFR");


        LinkedList<Booking> bks = new LinkedList<>();
        Booking bk1 = new Booking();
        Booking bk2 = new Booking();
        bks.add(bk1);
        bks.add(bk2);

        LinkedList<Booking> cancelled = new LinkedList<>();
        cancelled.add(bk2);
        cancelled.add(bk1);

        LinkedList<PilotLog> pls = new LinkedList();
        PilotLog pl1 = new PilotLog();
        PilotLog pl2 = new PilotLog();
        pls.add(pl1);
        pls.add(pl2);

        ArrayList<Plane> lop = new ArrayList<>();
        Plane pla1 = new Plane();
        Plane pla2 = new Plane();
        lop.add(pla1);
        lop.add(pla2);

        ArrayList<Instructor> loi = new ArrayList<>();
        Instructor i1 = new Instructor();
        Instructor i2 = new Instructor();
        loi.add(i1);
        loi.add(i2);

        Weather wx = new Weather();
        wx.metarUpdate("ZZZZ");
        wx.tafUpdate("YYYY");

        p1.setRatings(ratings);
        p1.setMedNum(12345);
        p1.setName("Mona");
        p1.setStudent(true);
        p1.setBookings(bks);
        p1.setPl(pls);
        p1.setCancelled(cancelled);
        p1.setToPostFlight(bks);
        p1.setCompletedBookings(cancelled);
        p1.setLop(lop);
        p1.setLoi(loi);
        p1.setWx(wx);

        assertEquals(ratings, p1.getRatings());
        assertEquals(12345, p1.getMedNum());
        assertEquals("Mona", p1.getName());
        assertTrue(p1.getStudentStatus());
        assertEquals(bks, p1.getBookings());
        assertEquals(pls, p1.getPl());
        assertEquals(cancelled, p1.getCancelled());
        assertEquals(bks, p1.getToPostFlight());
        assertEquals(cancelled, p1.getCompletedBookings());
        assertEquals(lop, p1.getLop());
        assertEquals(loi, p1.getLoi());
        assertEquals(wx, p1.getWx());
    }

    @Test
    public void addRatingTest() {
        p2.addRating("Float");
        assertTrue(p2.getRatings().contains("Float"));
        p2.addRating("IFR");
        assertTrue(p2.getRatings().contains("Float") && p2.getRatings().contains("IFR"));
    }

    @Test
    public void addBookingTest() {
        Booking b1 = new Booking();
        Booking b2 = new Booking();
        p1.addBooking(b1);
        assertTrue(p1.getBookings().contains(b1));
        p1.addBooking(b2);
        assertTrue(p1.getBookings().contains(b2) && p1.getBookings().contains(b1));
    }

    @Test
    public void equalsTest() {
        Booking bk = new Booking();

        assertFalse(p1.equals(bk));
        assertTrue(p2.equals(p1));
        assertTrue(p1.equals(p1));
        assertFalse(p1.equals(null));

        Pilot p = new Pilot();
        p.setStudent(true);
        p.setName("Donna");
        p.setMedNum(13999);

        assertFalse(p1.equals(p));

        Pilot pilot1 = new Pilot();
        pilot1.setName("SS");
        assertFalse(pilot1.equals(p1));

        HashSet<String> r = new HashSet<>();
        r.add("VFR");

        pilot1.setRatings(r);
        assertFalse(pilot1.equals(p1));

        pilot1.setMedNum(1123);
        assertFalse(pilot1.equals(p1));

        pilot1.setStudent(true);
        assertFalse(pilot1.equals(p1));

        LinkedList<Booking> bks = new LinkedList<>();
        bks.add(new Booking());
        bks.add(new Booking());
        bks.add(new Booking());

        pilot1.setBookings(bks);
        assertFalse(pilot1.equals(p1));

        bks.add(new Booking());
        pilot1.setToPostFlight(bks);
        assertFalse(pilot1.equals(p1));

        bks.add(new Booking());
        pilot1.setCancelled(bks);
        assertFalse(pilot1.equals(p1));

        bks.add(new Booking());
        pilot1.setCompletedBookings(bks);
        assertFalse(pilot1.equals(p1));

        LinkedList<PilotLog> pls = new LinkedList<>();
        pls.add(new PilotLog());
        pls.add(new PilotLog());
        pls.add(new PilotLog());
        pilot1.setPl(pls);

        assertFalse(pilot1.equals(p1));

        ArrayList<Plane> planes = new ArrayList<>();
        planes.add(new Plane());
        planes.add(new Plane());
        pilot1.setLop(planes);
        assertFalse(pilot1.equals(p1));

        ArrayList<Instructor> instructors = new ArrayList<>();
        instructors.add(new Instructor());
        instructors.add(new Instructor());
        pilot1.setLoi(instructors);
        assertFalse(pilot1.equals(p1));

        Weather wx1 = new Weather();
        wx1.metarUpdate("WWWW");
        wx1.tafUpdate("GGGG");
        pilot1.setWx(wx1);
        assertFalse(pilot1.equals(p1));

    }

    @Test
    public void hashCodeTest() {
        assertTrue(p1.equals(p2) && p2.equals(p1));
        assertTrue(p1.hashCode() == p2.hashCode());
    }

}
