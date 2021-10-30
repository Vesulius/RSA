package encryption;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import encryption.util.*;

import org.junit.jupiter.api.TestInstance;

@TestInstance(Lifecycle.PER_CLASS)
public class TimerTest {

    Timer timer;

    @BeforeAll
    public void setUp() {
        this.timer = new Timer();
    }

    @Test
    public void timingIsAccurate() throws InterruptedException {
        timer.start();
        long testStart = System.currentTimeMillis();
        Thread.sleep(10);
        double stop = timer.stop();
        long timePast = System.currentTimeMillis() - testStart;

        assertTrue(stop <= 11);
        assertTrue(stop >= 9);
        assertTrue(Math.abs(stop - timePast) < 2);
    }

    @Test
    public void lapsGiveAverage() throws InterruptedException {
        timer.start();
        long testStart = System.currentTimeMillis();
        double[] lapTimes = new double [10];
        for (int i = 0; i < lapTimes.length; i++) {
            Thread.sleep(5);
            lapTimes[i] = timer.lap();
        }
        for (int i = 0; i < lapTimes.length; i++) {
            assertTrue(lapTimes[i] <= 6);
            assertTrue(lapTimes[i] >= 4);
        }

        double stop = timer.stop();
        double averageTimePast = 1.0 * (System.currentTimeMillis() - testStart) / lapTimes.length;
        
        assertTrue(stop <= 6);
        assertTrue(stop >= 4);
        assertTrue(Math.abs(stop - averageTimePast) < 2);
    }
}
