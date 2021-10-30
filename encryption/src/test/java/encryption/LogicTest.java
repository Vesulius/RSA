package encryption;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Collections;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import encryption.math.*;
import encryption.util.*;

import org.junit.jupiter.api.TestInstance;

@TestInstance(Lifecycle.PER_CLASS)
public class LogicTest {
    
    Logic logic;
    RSA rsa;
    FileIO io;
    Primes primes;
    Testing test;
    String[] publicKey;
    String[] privateKey;

    @BeforeAll
    public void setUp() {
        primes = new Primes();
        rsa = new RSA();
        io = new FileIO();
        test = new Testing(io, primes);
        logic = new Logic(rsa, primes, io, test);
    }

    @BeforeEach
    public void generatesKeys() {
        logic.generate();
        
        publicKey = io.readMessage("public_key.txt").split("\n");
        privateKey = io.readMessage(".private_key.txt").split("\n");
        
        assertNotNull(publicKey);
        assertNotNull(privateKey);
        
        assertEquals(publicKey.length, 2);
        assertEquals(privateKey.length, 2);

        assertEquals(publicKey[1], privateKey[1]);
    }

    @Test
    public void encryptsMessage() {
        String message = "test";
        String encrypted = logic.encyptMessage(message);

        assertNotEquals(encrypted, message);
        assertEquals(rsa.decrypt(encrypted, privateKey), message);
    }

    @Test
    public void decryptsMessage() {
        String message = "test";
        String encrypted = rsa.encrypt(message, publicKey);
        io.writeMessage(encrypted, "encrypted.txt");
        assertEquals(logic.decryptMessage(), message);
    }
    
    @Test
    public void badTestInputWontThrowError() {
        ArrayList<String> test1 = new ArrayList<>();
        ArrayList<String> test2 = new ArrayList<>();
        ArrayList<String> test3 = new ArrayList<>();
        ArrayList<String> test4 = new ArrayList<>();

        Collections.addAll(test1, "timing", "asdf", "asdf");
        Collections.addAll(test2, "attempts", "asdf", "asdf");
        Collections.addAll(test3, "generation", "asdf", "asdf");
        Collections.addAll(test4, "asdf", "asdf", "asdf", "asdf", "asdf", "asdf");

        logic.test(test1);
        logic.test(test2);
        logic.test(test3);
        logic.test(test4);  
    }

    @Test
    public void testSaveResults() {
        ArrayList<String> test1 = new ArrayList<>();
        ArrayList<String> test2 = new ArrayList<>();
        ArrayList<String> test3 = new ArrayList<>();

        Collections.addAll(test1, "timing", "32", "5", "test1");
        Collections.addAll(test2, "attempts", "32", "5", "test2", "1");
        Collections.addAll(test3, "generation", "32", "5", "test3", "1", "y");

        logic.test(test1);
        logic.test(test2);
        logic.test(test3);

        assertTrue(io.readMessage("test1.txt").contains("mr-test"));
        assertTrue(io.readMessage("test1.txt").contains("bits: 32 rounds: 5 results in ms"));
        assertTrue(io.readMessage("test2.txt").contains("numberOfAttempts"));
        assertTrue(io.readMessage("test3.txt").contains("prime generation"));
    }
}
