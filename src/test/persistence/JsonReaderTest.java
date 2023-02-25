package persistence;

// Testing class for JsonReader
// CREDIT: code template from WorkRoomApp from https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
import model.Pilot;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class JsonReaderTest {
    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            Pilot p = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyPilot() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyPilot.json");
        try {
            Pilot p = reader.read();
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
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralPilot() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralPilot.json");
        try {
            Pilot p1 = reader.read();
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
            assertEquals("ZZZZ 130000Z 08009KT 10SM -RA FEW007 OVC021 06/06 A3006 RMK SC2SC6 SLP182=",
                    p1.getWx().getCurrentMetar());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
