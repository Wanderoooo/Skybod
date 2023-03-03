package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;
// Testing class for PlaneDocuments

public class PlaneDocumentsTest {
    private PlaneDocuments doc152;
    private PlaneDocuments pl1;
    private PlaneDocuments pl2;

    @BeforeEach
    public void runBefore() {
        doc152 = new PlaneDocuments();
        pl1 = new PlaneDocuments();
        pl2 = new PlaneDocuments();

    }

    @Test
    public void pdConstructorTest() {
        assertEquals(new LinkedList<>(), doc152.getFl());
        assertEquals("", doc152.getInsurance().getTypeOfInsurance());
        assertEquals(0.0, doc152.getWeightInfo());
    }

    @Test
    public void getterSetterTest() {
        Insurance c152ins = new Insurance();
        c152ins.setDateValid("11/30/2022");
        c152ins.setDateValidUntil("11/30/2023");
        c152ins.setAmountInsured(5000000);
        c152ins.setTypeOfInsurance("Liability, Hull");

        PlaneFlightLog c152fl = new PlaneFlightLog();
        c152fl.setArrivingAP("CYXX");
        c152fl.setDepartingAP("CYVR");
        c152fl.setHobbsTimeStart(95.6);
        c152fl.setHobbsTimeEnd(97.0);
        c152fl.setHoursTillMaint(3.0);

        LinkedList<PlaneFlightLog> logs152 = new LinkedList<>();
        logs152.add(c152fl);

        doc152.setFl(logs152);
        doc152.setWeightInfo(1138.26);
        doc152.setInsurance(c152ins);

        assertEquals(logs152, doc152.getFl());
        assertEquals(1138.26, doc152.getWeightInfo());
        assertEquals(c152ins, doc152.getInsurance());
    }

    @Test
    public void equalsTest() {
        assertTrue(doc152.equals(doc152));
        assertFalse(doc152.equals(null));
    }

    @Test
    public void hashCodeTest() {
        assertTrue(pl1.equals(pl2) && pl2.equals(pl1));
        assertTrue(pl1.hashCode() == pl2.hashCode());
    }
}
