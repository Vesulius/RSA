package encryption;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigInteger;
import java.util.Random;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import encryption.math.RSA;

import org.junit.jupiter.api.TestInstance;

@TestInstance(Lifecycle.PER_CLASS)
public class RSATest {
    
    RSA rsa;
    String message;
    String encryptedMessage;

    String[] publicKey;
    String[] privateKey;

    @BeforeAll
    public void generateKeysTest() {
        rsa = new RSA();

        BigInteger p = BigInteger.probablePrime(64, new Random());
        BigInteger q = BigInteger.probablePrime(64, new Random());
        BigInteger e = BigInteger.probablePrime(16, new Random());
        String[] keys = rsa.generateKeys(p, q, e);

        publicKey = new String[2];
        privateKey = new String[2];


        privateKey[0] = keys[0];
        privateKey[1] = keys[2];

        publicKey[0] = keys[1];
        publicKey[1] = keys[2];

        assertTrue(keys[0] instanceof String);
        assertTrue(keys[1] instanceof String);
        assertTrue(keys[2] instanceof String);

        message = "Test message";
        encryptedMessage = rsa.encrypt(message, publicKey);
    }

    @Test
    public void encryptTest() {
        String encrypted = rsa.encrypt(message, publicKey);

        assertFalse(encrypted.contains(message));
        assertEquals(encryptedMessage, encrypted);;
    }

    @Test
    public void decryptTest() {
        String decrypted = rsa.decrypt(encryptedMessage, privateKey);

        assertEquals(message, decrypted);
    }
}
