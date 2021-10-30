
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import encryption.math.*;
import encryption.util.*;

import org.junit.jupiter.api.TestInstance;

@TestInstance(Lifecycle.PER_CLASS)
public class TestingTest {

    FileIO io;
    Primes primes;
    Testing testing;

    @BeforeAll
    public void setUp() {
        primes = new Primes();
        io = new FileIO();
        testing = new Testing(io, primes);
    }

    @AfterAll
    public void cleanUp() {
        io.deleteFile("test1.txt");
        io.deleteFile("test2.txt");
        io.deleteFile("test3.txt");
    }

    @Test
    public void mrTestTimingSavesResults() {
        testing.mrTestTiming(16, 3, "test1");
        testing.mrTestTiming(32, 5, "test2");
        testing.mrTestTiming(64, 7, "test3");

        assertTrue(io.readMessage("test1.txt").contains("mr-test"));
        assertTrue(io.readMessage("test1.txt").contains("bits: 16 rounds: 3 results in ms"));
        assertTrue(io.readMessage("test2.txt").contains("bits: 32 rounds: 5 results in ms"));
        assertTrue(io.readMessage("test3.txt").contains("bits: 64 rounds: 7 results in ms"));
    }

    @Test
    public void averageNumberOfAttempts() {
        testing.averageNumberOfAttempts(16, 3, "test1", 1);
        testing.averageNumberOfAttempts(32, 5, "test2", 2);
        testing.averageNumberOfAttempts(64, 7, "test3", 3);

        assertTrue(io.readMessage("test1.txt").contains("numberOfAttempts"));
        assertTrue(io.readMessage("test1.txt").contains("bits: 16 testRounds: 3 mrRounds: 1"));
        assertTrue(io.readMessage("test2.txt").contains("bits: 32 testRounds: 5 mrRounds: 2"));
        assertTrue(io.readMessage("test3.txt").contains("bits: 64 testRounds: 7 mrRounds: 3"));
    }
}
