package model;

import java.util.ArrayList;
import java.util.Random;

public class Weather {

    private String currentMetar;
    private String currentTaf;
    private ArrayList<String> metars;
    private ArrayList<String> tafs;

    // EFFECT: create an empty weather record with current weather and forecast
    public Weather() {
        currentMetar = null;
        currentTaf = null;
        setUpWx();
    }

    // MODIFIES: this
    // EFFECT: generates an updated weather report at given airport in ICAO code (METAR), saves it.
    public void metarUpdate(String airport) {
        Random r = new Random();
        currentMetar = airport + " " + metars.get(r.nextInt(8));
    }

    // MODIFIES: this
    // EFFECT: generates an updated weather forecast at given airport in ICAO code (TAF), saves it.
    public void tafUpdate(String airport) {
        Random r = new Random();
        currentTaf = airport + " " + tafs.get(r.nextInt(3));
    }

    public String getCurrentTaf() {
        return currentTaf;
    }

    public String getCurrentMetar() {
        return currentMetar;
    }

    public ArrayList<String> getMetars() {
        return metars;
    }

    public ArrayList<String> getTafs() {
        return tafs;
    }

    public void setCurrentMetar(String currentMetar) {
        this.currentMetar = currentMetar;
    }

    public void setMetars(ArrayList<String> metars) {
        this.metars = metars;
    }

    public void setTafs(ArrayList<String> tafs) {
        this.tafs = tafs;
    }

    public void setCurrentTaf(String currentTaf) {
        this.currentTaf = currentTaf;
    }

    // MODIFIES: this
    // EFFECT: sets up possible metar & taf reports

    // CREDIT: METAR and TAF information are from NAVCANADA's aviation weather website:
    // https://flightplanning.navcanada.ca/cgi-bin/Fore-obs/metar.cgi?NoSession=NS_Inconnu&format=r
    // aw&Langue=anglais&Region=can&Stations=CYVR+CYXX+CYWH

    private void setUpWx() {
        metars = new ArrayList<>();
        metars.add("050000Z 09017KT 20SM SCT030 SCT080 OVC100 09/06 A2987 RMK SC3AC1AC4 SLP117=");
        metars.add("042300Z 09013G20KT 20SM BKN030 OVC038 09/07 A2989 RMK SC6SC2 SLP123");
        metars.add("08011G18KT 20SM OVC032 09/07 A2991 RMK SC8 SLP130=");
        metars.add("130100Z 09010KT 6SM -RA BR SCT007 OVC030 05/05 A3001 RMK SC3SC5 SLP166=");
        metars.add("130024Z 08010KT 3SM -RA BR SCT015 OVC029 06/06 A3004 RMK SC4NS4 PRESFR SLP176=");
        metars.add("130012Z CCA 08009KT 2 1/2SM RA BR SCT013 OVC029 06/06 A3006 RMK SC4NS4 SLP181=");
        metars.add("130012Z 08009KT 2 1/2SM -RA BR SCT013 OVC029 06/06 A3006 RMK SC4SC4 SLP181=");
        metars.add("130000Z 08009KT 10SM -RA FEW007 OVC021 06/06 A3006 RMK SC2SC6 SLP182=");

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

}
