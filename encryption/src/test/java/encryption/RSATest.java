package encryption;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
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
    Random random;

    String[] publicKey;
    String[] privateKey;

    @BeforeAll
    public void generateKeysTest() {
        rsa = new RSA();
        random = new Random();
        BigInteger p = BigInteger.probablePrime(64, random);
        BigInteger q = BigInteger.probablePrime(64, random);
        BigInteger e = BigInteger.probablePrime(16, random);
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
        assertNotEquals(keys[0], keys[1]);

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

    @Test
    public void cannotDecryptWithWrongKey() {
        BigInteger random1 = new BigInteger(1024, random);
        BigInteger random2 = new BigInteger(1024, random);

        String[] fakeKey =new String[2];
        fakeKey[0] = random1.toString();
        fakeKey[1] = random2.toString();

        String decrypted = rsa.decrypt(encryptedMessage, fakeKey);

        assertNotEquals(decrypted, message);
    }

    @Test
    public void differentKeysWontWork() {
        BigInteger p = BigInteger.probablePrime(64, random);
        BigInteger q = BigInteger.probablePrime(64, random);
        BigInteger e = BigInteger.probablePrime(16, random);
        String[] altKeys = rsa.generateKeys(p, q, e);

        String[] altPublicKey = new String[2];
        String[] altPrivateKey = new String[2];

        altPrivateKey[0] = altKeys[0];
        altPrivateKey[1] = altKeys[2];

        altPublicKey[0] = altKeys[1];
        altPublicKey[1] = altKeys[2];

        assertNotEquals(altPublicKey, publicKey);
        assertNotEquals(altPublicKey, publicKey);

        assertNotEquals(altPrivateKey, privateKey);
        assertNotEquals(altPrivateKey, privateKey);

        String altDecrypted = rsa.encrypt(message, altPublicKey);
        String decrypted = rsa.encrypt(message, publicKey);
        assertNotEquals(altDecrypted, decrypted);
    }
}
