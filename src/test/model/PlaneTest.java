package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;
// Testing class for Plane

public class PlaneTest {
    private Plane p1;
    private Plane p2;
    private Plane cessna172;

    @BeforeEach
    public void runBefore() {
        p1 = new Plane();
        p2 = new Plane();
        cessna172 = new Plane();
    }

    @Test
    public void planeConstructorTest() {
        assertEquals("", p1.getType());
        assertEquals("", p1.getCallSign());
        assertEquals(0, p1.getAvails().getSunday().size());
        assertEquals(0.0, p1.getPd().getWeightInfo());
        assertEquals(0, p1.getHourlyFuelRate());
        assertEquals(0, p1.getHourlyRentalRate());
        assertEquals(0.0, p1.getFuelAmount());
        assertEquals(0.0, p1.getMaxFuel());
    }

    @Test
    public void getterSetterTest() {
        DayTime cessna172DateTime = new DayTime();

        cessna172.setType("Cessna-172");
        cessna172.setCallSign("C-GOOV");
        cessna172DateTime = new DayTime();
        cessna172DateTime.addGivenDayTime("Monday", "0700", "2200");
        cessna172DateTime.addGivenDayTime("Tuesday", "0900", "2000");
        cessna172DateTime.addGivenDayTime("Wednesday", "0600", "1000");
        cessna172DateTime.addGivenDayTime("Thursday", "0800", "2100");
        cessna172DateTime.addGivenDayTime("Friday", "0400", "2400");
        cessna172DateTime.addGivenDayTime("Saturday", "0500", "2300");
        cessna172DateTime.addGivenDayTime("Sunday", "0200", "1900");
        cessna172.setAvails(cessna172DateTime);
        cessna172.setHourlyRentalRate(190);
        cessna172.setHourlyFuelRate(40);

        Insurance c172ins = new Insurance();
        c172ins.setDateValid("05/22/2022");
        c172ins.setDateValidUntil("05/22/2023");
        c172ins.setAmountInsured(3000000);
        c172ins.setTypeOfInsurance("Liability, Hull");

        PlaneFlightLog c172fl = new PlaneFlightLog();
        c172fl.setArrivingAP("CZBB");
        c172fl.setDepartingAP("CYVR");
        c172fl.setHobbsTimeStart(29.8);
        c172fl.setHobbsTimeEnd(30.1);
        c172fl.setHoursTillMaint(69.9);

        LinkedList<PlaneFlightLog> logs172 = new LinkedList<>();
        logs172.add(c172fl);

        PlaneDocuments doc172 = new PlaneDocuments();
        doc172.setFl(logs172);
        doc172.setWeightInfo(1209.13);
        doc172.setInsurance(c172ins);

        cessna172.setPd(doc172);
        cessna172.setFuelAmount(25.5);
        cessna172.setMaxFuel(48.0);

        assertEquals(cessna172DateTime, cessna172.getAvails());
        assertEquals("Cessna-172", cessna172.getType());
        assertEquals("C-GOOV", cessna172.getCallSign());
        assertEquals(190, cessna172.getHourlyRentalRate());
        assertEquals(40, cessna172.getHourlyFuelRate());
        assertEquals(doc172, cessna172.getPd());
        assertEquals(25.5, cessna172.getFuelAmount());
        assertEquals(48.0, cessna172.getMaxFuel());
    }

    @Test
    public void addFuelTest() {
        p1.setMaxFuel(30);
        p1.addFuel(0.1);
        assertEquals(0.1, p1.getFuelAmount());

        cessna172.setMaxFuel(45.1);
        cessna172.addFuel(45.1);
        assertEquals(45.1, cessna172.getFuelAmount());

        p1.addFuel(20.5);
        assertEquals(20.6, p1.getFuelAmount());
    }

    @Test
    public void setFuelToMaxFuelTest() {
        p1.setMaxFuel(20.5);
        p1.setFuelToMaxFuel();
        assertEquals(20.5, p1.getFuelAmount());
    }

    @Test
    public void equalsTest() {
        Booking bk = new Booking();

        assertFalse(p2.equals(bk));
        assertTrue(p2.equals(p1));
        assertTrue(p1.equals(p1));
        assertFalse(p1.equals(null));

        Plane p = new Plane();
        p.setType("Piper");
        assertFalse(p.equals(p1));

        Plane p1 = new Plane();
        p1.setType("Piper");
        p1.setCallSign("MSMD");
        assertFalse(p.equals(p1));

        Plane p2 = new Plane();
        ArrayList<String> day = new ArrayList<>();
        day.add("0903");
        DayTime dt = new DayTime();
        dt.setDay("monday", day);
        p2.setType("Piper");
        p2.setCallSign("MSMD");
        p2.setAvails(dt);
        assertFalse(p2.equals(p1));

        Plane p3 = new Plane();
        p3.setType("Piper");
        p3.setCallSign("MSMD");
        p3.setAvails(dt);
        p3.setHourlyFuelRate(6);
        assertFalse(p3.equals(p2));

        Plane p4 = new Plane();
        p4.setType("Piper");
        p4.setCallSign("MSMD");
        p4.setAvails(dt);
        p4.setHourlyFuelRate(6);
        p4.setHourlyRentalRate(34);
        assertFalse(p4.equals(p3));

        Plane p5 = new Plane();
        PlaneDocuments pdo = new PlaneDocuments();
        pdo.setWeightInfo(23999);
        p5.setType("Piper");
        p5.setCallSign("MSMD");
        p5.setAvails(dt);
        p5.setHourlyFuelRate(6);
        p5.setHourlyRentalRate(34);
        p5.setPd(pdo);
        assertFalse(p5.equals(p4));

        Plane p6 = new Plane();
        p6.setType("Piper");
        p6.setCallSign("MSMD");
        p6.setAvails(dt);
        p6.setHourlyFuelRate(6);
        p6.setHourlyRentalRate(34);
        p6.setPd(pdo);
        p6.setFuelAmount(23424);
        assertFalse(p6.equals(p5));


        Plane p8 = new Plane();
        p8.setFuelAmount(299);
        p8.setType("Piper");
        p8.setCallSign("MSMD");
        p8.setAvails(dt);
        p8.setHourlyFuelRate(6);
        p8.setHourlyRentalRate(34);
        p8.setPd(pdo);
        p8.setFuelAmount(23424);
        p8.setMaxFuel(888);
        assertFalse(p8.equals(p6));

    }

    @Test
    public void hashCodeTest() {
        assertTrue(p1.equals(p2) && p2.equals(p1));
        assertTrue(p1.hashCode() == p2.hashCode());
    }
}
