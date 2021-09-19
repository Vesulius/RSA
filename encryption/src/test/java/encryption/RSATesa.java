package encryption;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class RSATesa {
    
    RSA rsa;
    String message;
    String encryptedMessage;

    @BeforeAll
    public void generateKeysTest() {
        rsa = new RSA();
        rsa.generateKeys();

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

        assertEquals(encryptedMessage, decrypted);
    }
}
