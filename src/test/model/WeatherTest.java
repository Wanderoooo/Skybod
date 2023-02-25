package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
// Testing class for Weather

public class WeatherTest {
    private Weather wx;

    @BeforeEach
    public void runBefore() {
        wx = new Weather();
    }

    @Test
    public void weatherConstructorTest() {
        assertEquals("", wx.getCurrentTaf());
        assertEquals("", wx.getCurrentMetar());

        ArrayList<String> metars = new ArrayList<>();
        metars.add("050000Z 09017KT 20SM SCT030 SCT080 OVC100 09/06 A2987 RMK SC3AC1AC4 SLP117=");
        metars.add("042300Z 09013G20KT 20SM BKN030 OVC038 09/07 A2989 RMK SC6SC2 SLP123");
        metars.add("08011G18KT 20SM OVC032 09/07 A2991 RMK SC8 SLP130=");
        metars.add("130100Z 09010KT 6SM -RA BR SCT007 OVC030 05/05 A3001 RMK SC3SC5 SLP166=");
        metars.add("130024Z 08010KT 3SM -RA BR SCT015 OVC029 06/06 A3004 RMK SC4NS4 PRESFR SLP176=");
        metars.add("130012Z CCA 08009KT 2 1/2SM RA BR SCT013 OVC029 06/06 A3006 RMK SC4NS4 SLP181=");
        metars.add("130012Z 08009KT 2 1/2SM -RA BR SCT013 OVC029 06/06 A3006 RMK SC4SC4 SLP181=");
        metars.add("130000Z 08009KT 10SM -RA FEW007 OVC021 06/06 A3006 RMK SC2SC6 SLP182=");

        ArrayList<String> tafs = new ArrayList<>();
        tafs.add("042340Z 0500/0606 10012G22KT P6SM SCT012 BKN025 TEMPO\n"
                + "0500/0504 P6SM -RA BKN015\n"
                + "FM050400 09010KT P6SM SCT020 BKN060\n"
                + "FM050900 09008KT P6SM SCT060 BKN200 TEMPO 0509/0516 BKN060\n"
                + "FM051600 08008KT P6SM BKN040 TEMPO 0516/0606 P6SM -RA BKN020\n"
                + "RMK NXT FCST BY 050300Z");
        tafs.add("042340Z 0500/0524 VRB03KT P6SM -RA SCT015 BKN025 TEMPO\n"
                + "0500/0505 5SM -RA BR BKN012\n"
                + "FM050500 04008KT P6SM SCT060 BKN120 TEMPO 0505/0509 BKN050\n"
                + "FM051800 VRB03KT P6SM BKN050 TEMPO 0518/0524 P6SM -RA BKN020\n"
                + "RMK NXT FCST BY 050600Z=");
        tafs.add("042343Z 0500/0505 VRB03KT P6SM BKN100\n"
                + "RMK NXT FCST BY 051500Z=");

        assertEquals(tafs, wx.getTafs());
        assertEquals(metars, wx.getMetars());
    }

    @Test
    public void getterSetterTest() {
        wx.setCurrentMetar("currMet");
        wx.setCurrentTaf("currTaf");
        ArrayList<String> metars = new ArrayList<>();
        metars.add("test1");
        metars.add("test2");
        wx.setMetars(metars);
        ArrayList<String> tafs = new ArrayList<>();
        tafs.add("test3");
        tafs.add("test4");
        wx.setTafs(tafs);

        assertEquals(tafs, wx.getTafs());
        assertEquals(metars, wx.getMetars());
        assertEquals("currMet", wx.getCurrentMetar());
        assertEquals("currTaf", wx.getCurrentTaf());
    }

    // CREDIT: the following two tests for randomness includes code inspired by "coin toss" practice problem from EDX:
    // https://learning.edge.edx.org/course/course-v1:UBC+CPSC210+2022W2/block-v1:UBC+CPSC210+2022W2+type@sequential
    // +block@407e32d6ee6b4fcf8e36a4514c03fbe4/block-v1:UBC+CPSC210+2022W2+type@vertical+block@23bcce9e22e2469eaa289b1
    // df6a328ca

    @Test
    public void tafUpdateTest() {
        int timesChanged = 0;
        String lastStatus = wx.getCurrentTaf();

        for (int i = 0; i < 100; i++) {
            wx.tafUpdate("CZBB");
            String currTaf = wx.getCurrentTaf();

            assertTrue(wx.getCurrentTaf().equals("CZBB 042340Z 0500/0606 10012G22KT P6SM SCT012 BKN025 TEMPO\n"
                    + "0500/0504 P6SM -RA BKN015\n"
                    + "FM050400 09010KT P6SM SCT020 BKN060\n"
                    + "FM050900 09008KT P6SM SCT060 BKN200 TEMPO 0509/0516 BKN060\n"
                    + "FM051600 08008KT P6SM BKN040 TEMPO 0516/0606 P6SM -RA BKN020\n"
                    + "RMK NXT FCST BY 050300Z") || wx.getCurrentTaf().equals("CZBB 042340Z 0500/0524 VRB03KT P6SM "
                    + "-RA SCT015 BKN025 TEMPO\n"
                    + "0500/0505 5SM -RA BR BKN012\n"
                    + "FM050500 04008KT P6SM SCT060 BKN120 TEMPO 0505/0509 BKN050\n"
                    + "FM051800 VRB03KT P6SM BKN050 TEMPO 0518/0524 P6SM -RA BKN020\n"
                    + "RMK NXT FCST BY 050600Z=") || wx.getCurrentTaf().equals("CZBB 042343Z 0500/0505 VRB03KT P6SM "
                    + "BKN100\n"
                    + "RMK NXT FCST BY 051500Z="));

            if (!currTaf.equals(lastStatus)) {
                timesChanged++;
                lastStatus = currTaf;
            }
        }

        assertFalse(timesChanged == 0);
    }

    @Test
    public void metarUpdateTest() {
        int timesChanged = 0;
        String lastStatus = wx.getCurrentMetar();

        for (int i = 0; i < 100; i++) {
            wx.metarUpdate("CZBB");
            String currMetar = wx.getCurrentMetar();

            assertTrue(wx.getCurrentMetar().equals("CZBB 050000Z 09017KT 20SM SCT030 SCT080 OVC100"
                    + " 09/06 A2987 RMK SC3AC1AC4 SLP117=")
            || wx.getCurrentMetar().equals("CZBB 042300Z 09013G20KT 20SM BKN030 OVC038 09/07 A2989 RMK SC6SC2 SLP123")
            || wx.getCurrentMetar().equals("CZBB 08011G18KT 20SM OVC032 09/07 A2991 RMK SC8 SLP130=")
            || wx.getCurrentMetar().equals("CZBB 130100Z 09010KT 6SM -RA BR SCT007 OVC030 05/05 "
                    + "A3001 RMK SC3SC5 SLP166=")
            || wx.getCurrentMetar().equals("CZBB 130024Z 08010KT 3SM -RA BR SCT015 OVC029 06/06 A3004 RMK "
                    + "SC4NS4 PRESFR SLP176=")
            || wx.getCurrentMetar().equals("CZBB 130012Z CCA 08009KT 2 1/2SM RA BR SCT013 OVC029 06/06 "
                    + "A3006 RMK SC4NS4 SLP181=")
            || wx.getCurrentMetar().equals("CZBB 130012Z 08009KT 2 1/2SM -RA BR SCT013 OVC029 06/06 A3006 RMK "
                    + "SC4SC4 SLP181=")
            || wx.getCurrentMetar().equals("CZBB 130000Z 08009KT 10SM -RA FEW007 OVC021 06/06 A3006"
                    + " RMK SC2SC6 SLP182="));

            if (!currMetar.equals(lastStatus)) {
                timesChanged++;
                lastStatus = currMetar;
            }
        }

        assertFalse(timesChanged == 0);
    }

}
