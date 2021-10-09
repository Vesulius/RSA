package encryption;

import java.math.BigInteger;

/**
 * 
 * Main logic for the program
 * 
 * <p>
 * Responsible for connecting the ui to the encryption service and file i/o
 * </P>
 * 
 */
public class Logic {
    
    private RSA rsa;
    private Primes primes;
    private FileIO io;

    public Logic(RSA rsa, Primes primes, FileIO io) {
        this.rsa = rsa;
        this.primes = primes;
        this.io = io;
    }
    
    /**
     * 
     * Generates the keys needed for encyption and decryption
     * 
     */
    public void generate() {
        BigInteger p = primes.generate(1024);
        BigInteger q = primes.generate(1024);
        BigInteger e = primes.generate(16);

        rsa.generateKeys(p, q, e);
    }
    
    /**
     * 
     * Encrypts given message and saves the encypted message to file
     * 
     * @param message String message to be encrypted
     * @return encrypted message as String
     */
    public String encyptMessage(String message) {
        String encrypted = rsa.encrypt(message);
        io.writeMessage(encrypted, "encrypted.txt");
        return encrypted;
    } 

    /**
     * 
     * Reads the encrypted message from file and decrypts it
     * 
     * @return String message decrypted
     */
    public String decryptMessage() {
        String encypted = io.readMessage("encrypted.txt");
        return rsa.decrypt(encypted);
    }
}
