package encryption;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

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

    @BeforeAll
    public void generateKeysTest() {
        rsa = new RSA();

        BigInteger p = BigInteger.probablePrime(64, new Random());
        BigInteger q = BigInteger.probablePrime(64, new Random());
        BigInteger e = BigInteger.probablePrime(16, new Random());
        rsa.generateKeys(p, q, e);

        message = "Test message";
        encryptedMessage = rsa.encrypt(message);
    }

    @Test
    public void encryptTest() {
        String encrypted = rsa.encrypt(message);

        assertFalse(encrypted.contains(message));
        assertEquals(encryptedMessage, encrypted);;
    }

    @Test
    public void decryptTest() {
        String decrypted = rsa.decrypt(encryptedMessage);

        assertEquals(message, decrypted);
    }
}
