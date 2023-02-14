package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
// Testing class for PostFlight

public class PostFlightTest {
    private Postflight pf;

    @BeforeEach
    public void runBefore() {
        pf = new Postflight();
    }

    @Test
    public void pfConstructorTest() {
        assertFalse(pf.getIsPlaneTiedDown());
        assertFalse(pf.getDocComplete());
        assertEquals(0.0, pf.getEndHobbsTime());
    }

    @Test
    public void getterSetterTest() {
        pf.setDocComplete(true);
        pf.setPlaneTiedDown(true);
        pf.setEndHobbsTime(0.0);

        assertTrue(pf.getDocComplete());
        assertTrue(pf.getIsPlaneTiedDown());
        assertEquals(0.0, pf.getEndHobbsTime());

        pf.setEndHobbsTime(1.2);
        assertEquals(1.2, pf.getEndHobbsTime());

        pf.setEndHobbsTime(0.00001);
        assertEquals(0.00001, pf.getEndHobbsTime());
    }
}
