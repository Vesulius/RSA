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

        assertNotNull(rsa.privateKey[0]);
        assertNotNull(rsa.privateKey[1]);
        assertNotNull(rsa.publicKey[0]);
        assertNotNull(rsa.publicKey[1]);
    }

    @Test
    public void encryptsMessage() {
        String message = "test";
        String encrypted = logic.encyptMessage(message);

        assertNotEquals(encrypted, message);
        assertEquals(rsa.decrypt(encrypted), message);
    }

    @Test
    public void decryptsMessage() {
        String message = "test";
        String encrypted = rsa.encrypt(message);
        io.writeMessage(encrypted, "encrypted.txt");
        assertEquals(logic.decryptMessage(), message);
    }


}
