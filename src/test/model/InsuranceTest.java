package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class InsuranceTest {
    private Insurance ins1;

    @BeforeEach
    public void runBefore() {
        ins1 = new Insurance();
    }

    @Test
    public void insuranceConstructorTest() {
        assertNull(ins1.getDateValid());
        assertNull(ins1.getTypeOfInsurance());
        assertNull(ins1.getDateValidUntil());
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
}
