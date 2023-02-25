package persistence;

import model.*;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

// Testing class for JsonWriter
// CREDIT: code template from WorkRoomApp from https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git

class JsonWriterTest {

    @Test
    void testWriterInvalidFile() {
        try {
            Pilot p = new Pilot();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyPilot() {
        try {
            Pilot p = new Pilot();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyPilot.json");
            writer.open();
            writer.write(p);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyPilot.json");
            p = reader.read();
            assertEquals("", p.getName());
            assertEquals(0, p.getRatings().size());
            assertEquals(0, p.getMedNum());
            assertFalse(p.getStudentStatus());
            assertEquals(0, p.getBookings().size());
            assertEquals(0, p.getPl().size());
            assertEquals(0, p.getCancelled().size());
            assertEquals(0, p.getToPostFlight().size());
            assertEquals(0, p.getCompletedBookings().size());
            assertEquals(0, p.getLoi().size());
            assertEquals(0, p.getLop().size());
            assertEquals("", p.getWx().getCurrentTaf());

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralPilot() {
        try {
            Pilot p1 = new Pilot();
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

            Weather wx = new Weather();
            wx.metarUpdate("ZZZZ");
            wx.tafUpdate("YYYY");

            ArrayList<Instructor> loi = new ArrayList<>();
            Instructor i1 = new Instructor();
            Instructor i2 = new Instructor();
            loi.add(i1);
            loi.add(i2);

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

            JsonWriter writer = new JsonWriter("./data/testWriterGeneralPilot.json");
            writer.open();
            writer.write(p1);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralPilot.json");
            p1 = reader.read();
            assertEquals(3, p1.getRatings().size());
            assertEquals(12345, p1.getMedNum());
            assertEquals("Mona", p1.getName());
            assertTrue(p1.getStudentStatus());
            assertEquals(2, p1.getBookings().size());
            assertEquals(2, p1.getPl().size());
            assertEquals(2, p1.getCancelled().size());
            assertEquals(2, p1.getToPostFlight().size());
            assertEquals(2, p1.getCompletedBookings().size());
            assertEquals(2, p1.getLop().size());
            assertEquals(2, p1.getLoi().size());
            assertEquals(wx.getCurrentMetar(), p1.getWx().getCurrentMetar());

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
