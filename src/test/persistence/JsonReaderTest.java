package persistence;

// Testing class for JsonReader
// CREDIT: code template from WorkRoomApp from https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
import model.Pilot;
import model.Plane;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

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
            assertEquals("", p.getWx().getCurrentMetar());
            assertEquals("042340Z 0500/0606 10012G22KT P6SM SCT012 BKN025 TEMPO\n0500/0504 P6SM -RA BKN015\nFM"
                    + "050400 09010KT P6SM SCT020 BKN060\nFM050900 09008KT P6SM SCT060 BKN200 TEMPO 0509/0516 BKN060\n"
                    + "FM051600 08008KT P6SM BKN040 TEMPO 0516/0606 P6SM -RA BKN020\nRMK NXT FCST BY 050300Z",
                    p.getWx().getTafs().get(0));
            assertEquals("042340Z 0500/0524 VRB03KT P6SM -RA SCT015 BKN025 TEMPO\n0500/0505 5SM -RA BR BKN012\n"
                    + "FM050500 04008KT P6SM SCT060 BKN120 TEMPO 0505/0509 BKN050\nFM051800 VRB03KT P6SM BKN050 TEMPO"
                    + " 0518/0524 P6SM -RA BKN020\nRMK NXT FCST BY 050600Z=", p.getWx().getTafs().get(1));
            assertEquals("042343Z 0500/0505 VRB03KT P6SM BKN100\nRMK NXT FCST BY 051500Z=",
                    p.getWx().getTafs().get(2));
            assertEquals("050000Z 09017KT 20SM SCT030 SCT080 OVC100 09/06 A2987 RMK SC3AC1AC4 SLP117=",
                    p.getWx().getMetars().get(0));
            assertEquals("042300Z 09013G20KT 20SM BKN030 OVC038 09/07 A2989 RMK SC6SC2 SLP123",
                    p.getWx().getMetars().get(1));
            assertEquals("08011G18KT 20SM OVC032 09/07 A2991 RMK SC8 SLP130=", p.getWx().getMetars().get(2));
            assertEquals("130100Z 09010KT 6SM -RA BR SCT007 OVC030 05/05 A3001 RMK SC3SC5 SLP166=",
                    p.getWx().getMetars().get(3));
            assertEquals("130024Z 08010KT 3SM -RA BR SCT015 OVC029 06/06 A3004 RMK SC4NS4 PRESFR SLP176=",
                    p.getWx().getMetars().get(4));
            assertEquals("130012Z CCA 08009KT 2 1/2SM RA BR SCT013 OVC029 06/06 A3006 RMK SC4NS4 SLP181=",
                    p.getWx().getMetars().get(5));
            assertEquals("130012Z 08009KT 2 1/2SM -RA BR SCT013 OVC029 06/06 A3006 RMK SC4SC4 SLP181=",
                    p.getWx().getMetars().get(6));
            assertEquals("130000Z 08009KT 10SM -RA FEW007 OVC021 06/06 A3006 RMK SC2SC6 SLP182=",
                    p.getWx().getMetars().get(7));

        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderDayTime() {
        JsonWriter writer = new JsonWriter("./data/testReaderDayTime.json");
        JsonReader reader = new JsonReader("./data/testReaderDayTime.json");
        try {
            Pilot pb = new Pilot();

            ArrayList<String> oneDay = new ArrayList<>();
            oneDay.add("0000");
            Plane plane = new Plane();
            plane.getAvails().setDay("monday", oneDay);
            plane.getAvails().setDay("tuesday", oneDay);
            plane.getAvails().setDay("wednesday", oneDay);
            plane.getAvails().setDay("thursday", oneDay);
            plane.getAvails().setDay("friday", oneDay);
            plane.getAvails().setDay("saturday", oneDay);
            plane.getAvails().setDay("sunday", oneDay);
            pb.getLop().add(plane);

            writer.open();
            writer.write(pb);
            writer.close();

            Pilot pa = reader.read();
            assertTrue(pa.getLop().contains(plane));
            assertTrue(pa.getLop().get(0).equals(plane));

        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

}

