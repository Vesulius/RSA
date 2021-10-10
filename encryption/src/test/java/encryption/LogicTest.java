package encryption;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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
    String[] publicKey;
    String[] privateKey;

    @BeforeAll
    public void setUp() {
        primes = new Primes();
        rsa = new RSA();
        io = new FileIO();
        logic = new Logic(rsa, primes, io);
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


}
