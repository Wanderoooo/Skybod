package model;

import java.util.ArrayList;
import java.util.Random;

public class Weather {

    private String currentMetar;
    private String currentTaf;
    private ArrayList<String> metars;
    private ArrayList<String> tafs;

    // EFFECT: !!!
    public Weather() {
        currentMetar = "";
        currentTaf = "";

        setUpWx();
    }

    // MODIFIES: this
    // EFFECT: generates an updated weather report in ICAO code (METAR), saves it.
    public void metarUpdate(String airport) {
        Random r = new Random();
        currentMetar = airport + " " + metars.get(r.nextInt(3));
    }

    // MODIFIES: this
    // EFFECT: generates an updated weather forecast in ICAO code (TAF), saves it.
    public void tafUpdate(String airport) {
        Random r = new Random();
        currentTaf = airport + " " + tafs.get(r.nextInt(3));
    }

    private void setUpWx() {
        metars = new ArrayList<>();
        metars.add("050000Z 09017KT 20SM SCT030 SCT080 OVC100 09/06 A2987 RMK SC3AC1AC4 SLP117=");
        metars.add("042300Z 09013G20KT 20SM BKN030 OVC038 09/07 A2989 RMK SC6SC2 SLP123");
        metars.add("08011G18KT 20SM OVC032 09/07 A2991 RMK SC8 SLP130=");

        tafs = new ArrayList<>();
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
    }

    public String getCurrentMetar() {
        return currentMetar;
    }

    public String getCurrentTaf() {
        return currentTaf;
    }
}
