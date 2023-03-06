package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
// Testing class for Insurance

public class InsuranceTest {
    private Insurance ins1;
    private Insurance ins2;

    @BeforeEach
    public void runBefore() {
        ins1 = new Insurance();
        ins2 = new Insurance();
    }

    @Test
    public void insuranceConstructorTest() {
        assertEquals("", ins1.getDateValid());
        assertEquals("", ins1.getTypeOfInsurance());
        assertEquals("", ins1.getDateValidUntil());
        assertEquals(0, ins1.getAmountInsured());
    }

    @Test
    public void getterSetterTest() {
        ins1.setAmountInsured(200);
        ins1.setTypeOfInsurance("Haul");
        ins1.setDateValidUntil("11/02/2024");
        ins1.setDateValid("11/02/2022");

        assertEquals(200, ins1.getAmountInsured());
        assertEquals("Haul", ins1.getTypeOfInsurance());
        assertEquals("11/02/2024", ins1.getDateValidUntil());
        assertEquals("11/02/2022", ins1.getDateValid());
    }

    @Test
    public void equalsTest() {
        Booking bk = new Booking();

        assertFalse(ins1.equals(bk));
        assertTrue(ins2.equals(ins1));
        assertTrue(ins1.equals(ins1));
        assertFalse(ins1.equals(null));

        Insurance i = new Insurance();
        i.setTypeOfInsurance("Haul");
        i.setAmountInsured(12);
        i.setDateValid("11");
        i.setDateValidUntil("12");

        assertFalse(ins1.equals(i));

        Insurance i1 = new Insurance();
        i1.setDateValid("3234");
        assertFalse(i1.equals(ins1));

        Insurance i2 = new Insurance();
        i2.setDateValid("3234");
        i2.setAmountInsured(2);
        assertFalse(i2.equals(i1));

        Insurance i3 = new Insurance();
        i3.setDateValid("3234");
        i3.setAmountInsured(2);
        i3.setDateValidUntil("4345");
        assertFalse(i3.equals(i2));

        Insurance i4 = new Insurance();
        i4.setDateValid("3234");
        i4.setAmountInsured(2);
        i4.setDateValidUntil("4345");
        i4.setTypeOfInsurance("Haul & Wings");
        assertFalse(i4.equals(i3));

    }

    @Test
    public void hashCodeTest() {
        assertTrue(ins1.equals(ins2) && ins2.equals(ins1));
        assertTrue(ins1.hashCode() == ins2.hashCode());
    }
}
