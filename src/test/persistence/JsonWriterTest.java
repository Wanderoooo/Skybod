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
            Pilot p2 = reader.read();
            assertTrue(p1.equals(p2));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterLotOfInfoPilot() {
        try {
            Pilot p = new Pilot();

            PlaneFlightLog fl = new PlaneFlightLog();
            fl.setArrivingAP("ZZTT");
            fl.setDepartingAP("TTZZ");
            fl.setHoursTillMaint(20.0);
            fl.setHobbsTimeStart(1.1);
            fl.setHobbsTimeEnd(2.2);

            PlaneDocuments pd = new PlaneDocuments();
            pd.setWeightInfo(50.0);
            pd.getFl().add(fl);

            ArrayList<String> monday = new ArrayList<>();
            monday.add("0000");
            monday.add("0100");
            monday.add("0200");

            DayTime dt = new DayTime();
            dt.setDay("monday", monday);

            Plane pl = new Plane();
            pl.setAvails(dt);
            pl.setPd(pd);

            HashSet<String> ratings = new HashSet<>();
            ratings.add("Float");
            ratings.add("VFR");

            Instructor i = new Instructor();
            i.setRatings(ratings);

            p.getLop().add(pl);
            p.getLoi().add(i);

            JsonWriter writer = new JsonWriter("./data/testWriterLotOfInfoPilot.json");
            writer.open();
            writer.write(p);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterLotOfInfoPilot.json");
            Pilot p1 = reader.read();
            assertTrue(p.equals(p1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
