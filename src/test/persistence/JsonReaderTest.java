package persistence;

// Testing class for JsonReader
// CREDIT: code template from WorkRoomApp from https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
import model.Pilot;
import model.Plane;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class JsonReaderTest {
    private Pilot pilotOneItemEach;

    @BeforeEach
    void runBefore() {
        pilotOneItemEach = new Pilot();


    }
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

//    @Test
//    void testReaderGeneralPilot() {
//        JsonReader reader = new JsonReader("./data/testReaderGeneralPilot.json");
//        try {
//            Pilot p1 = reader.read();
//            assertEquals(3, p1.getRatings().size());
//            assertEquals(12345, p1.getMedNum());
//            assertEquals("Mona", p1.getName());
//            assertTrue(p1.getStudentStatus());
//            assertEquals(2, p1.getBookings().size());
//            assertEquals(2, p1.getPl().size());
//            assertEquals(2, p1.getCancelled().size());
//            assertEquals(2, p1.getToPostFlight().size());
//            assertEquals(2, p1.getCompletedBookings().size());
//            assertEquals(2, p1.getLop().size());
//            assertEquals(2, p1.getLoi().size());
//            assertEquals("ZZZZ 130000Z 08009KT 10SM -RA FEW007 OVC021 06/06 A3006 RMK SC2SC6 SLP182=",
//                    p1.getWx().getCurrentMetar());
//        } catch (IOException e) {
//            fail("Couldn't read from file");
//        }
//    }
//
//    @Test
//    void testReaderLotOfInfoPilot() {
//        JsonReader reader = new JsonReader("./data/testReaderLotOfInfoPilot.json");
//        try {
//            Pilot p = reader.read();
//            assertEquals(1, p.getBookings().size());
//            assertEquals("0200", p.getBookings().get(0).getTimeBooked());
//            assertEquals(1, p.getToPostFlight().size());
//            assertFalse(p.getLop().get(0).getAvails().getTuesday().contains("0200"));
//            assertTrue(p.getRatings().contains("VFR"));
//
//            Plane cessna152 = new Plane();
//            for (Plane plane : p.getLop())  {
//                if (plane.getType().equalsIgnoreCase("cessna-152")) {
//                    cessna152 = plane;
//                }
//            }
//
//            assertFalse(cessna152.getPd().getFl().size() == 0);
//
//        } catch (IOException e) {
//            fail("Couldn't read from file");
//        }
//    }
}

