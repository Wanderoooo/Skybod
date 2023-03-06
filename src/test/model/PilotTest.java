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

        Pilot pl1 = new Pilot();
        pl1.setName("SS");
        assertFalse(pl1.equals(p1));

        Pilot pl2 = new Pilot();
        HashSet<String> r = new HashSet<>();
        r.add("VFR");
        pl2.setName("SS");
        pl2.setRatings(r);
        assertFalse(pl2.equals(pl1));

        Pilot pl3 = new Pilot();
        pl3.setName("SS");
        pl3.setRatings(r);
        pl3.setMedNum(1123);
        assertFalse(pl3.equals(pl2));

        Pilot pl4 = new Pilot();
        pl4.setName("SS");
        pl4.setRatings(r);
        pl4.setMedNum(1123);
        pl4.setStudent(true);
        assertFalse(pl4.equals(pl3));

        Pilot pl5 = new Pilot();
        LinkedList<Booking> bks = new LinkedList<>();
        bks.add(new Booking());
        bks.add(new Booking());
        bks.add(new Booking());

        pl5.setName("SS");
        pl5.setRatings(r);
        pl5.setMedNum(1123);
        pl5.setStudent(true);
        pl5.setBookings(bks);
        assertFalse(pl5.equals(pl4));

        Pilot pl6 = new Pilot();
        LinkedList<Booking> bks1 = new LinkedList<>();
        bks1.add(new Booking());
        pl6.setName("SS");
        pl6.setRatings(r);
        pl6.setMedNum(1123);
        pl6.setStudent(true);
        pl6.setBookings(bks);
        pl6.setToPostFlight(bks1);
        assertFalse(pl6.equals(pl5));

        Pilot pl7 = new Pilot();
        bks.add(new Booking());
        pl7.setName("SS");
        pl7.setRatings(r);
        pl7.setMedNum(1123);
        pl7.setStudent(true);
        pl7.setBookings(bks);
        pl7.setToPostFlight(bks1);
        pl7.setCancelled(bks);
        assertFalse(pl7.equals(pl6));

        Pilot pl8 = new Pilot();
        bks.add(new Booking());
        pl8.setName("SS");
        pl8.setRatings(r);
        pl8.setMedNum(1123);
        pl8.setStudent(true);
        pl8.setBookings(bks);
        pl8.setToPostFlight(bks1);
        pl8.setCancelled(bks);
        pl8.setCompletedBookings(bks);
        assertFalse(pl8.equals(pl7));

        Pilot pl9 = new Pilot();
        LinkedList<PilotLog> pls = new LinkedList<>();
        pls.add(new PilotLog());
        pls.add(new PilotLog());
        pls.add(new PilotLog());
        pl9.setPl(pls);
        pl9.setName("SS");
        pl9.setRatings(r);
        pl9.setMedNum(1123);
        pl9.setStudent(true);
        pl9.setBookings(bks);
        pl9.setToPostFlight(bks1);
        pl9.setCancelled(bks);
        pl9.setCompletedBookings(bks);

        assertFalse(pl9.equals(pl8));

        Pilot pl10 = new Pilot();
        ArrayList<Plane> planes = new ArrayList<>();
        planes.add(new Plane());
        planes.add(new Plane());
        pl10.setPl(pls);
        pl10.setName("SS");
        pl10.setRatings(r);
        pl10.setMedNum(1123);
        pl10.setStudent(true);
        pl10.setBookings(bks);
        pl10.setToPostFlight(bks1);
        pl10.setCancelled(bks);
        pl10.setCompletedBookings(bks);
        pl10.setLop(planes);
        assertFalse(pl10.equals(pl9));

        Pilot pl11 = new Pilot();
        ArrayList<Instructor> instructors = new ArrayList<>();
        instructors.add(new Instructor());
        instructors.add(new Instructor());
        pl11.setPl(pls);
        pl11.setName("SS");
        pl11.setRatings(r);
        pl11.setMedNum(1123);
        pl11.setStudent(true);
        pl11.setBookings(bks);
        pl11.setToPostFlight(bks1);
        pl11.setCancelled(bks);
        pl11.setCompletedBookings(bks);
        pl11.setLop(planes);
        pl11.setLoi(instructors);
        assertFalse(pl11.equals(pl10));

        Pilot pl12 = new Pilot();
        Weather wx1 = new Weather();
        wx1.metarUpdate("WWWW");
        wx1.tafUpdate("GGGG");
        pl12.setPl(pls);
        pl12.setName("SS");
        pl12.setRatings(r);
        pl12.setMedNum(1123);
        pl12.setStudent(true);
        pl12.setBookings(bks);
        pl12.setToPostFlight(bks1);
        pl12.setCancelled(bks);
        pl12.setCompletedBookings(bks);
        pl12.setLop(planes);
        pl12.setLoi(instructors);
        pl12.setWx(wx1);
        assertFalse(pl12.equals(pl11));

    }

    @Test
    public void hashCodeTest() {
        assertTrue(p1.equals(p2) && p2.equals(p1));
        assertTrue(p1.hashCode() == p2.hashCode());
    }

}
