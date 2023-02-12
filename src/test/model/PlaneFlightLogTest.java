package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PlaneFlightLogTest {
    PlaneFlightLog fl;

    @BeforeEach
    public void runBefore() {
        fl = new PlaneFlightLog();
    }
}
