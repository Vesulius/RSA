package encryption.util;

import java.math.BigInteger;

import encryption.math.Primes;
import encryption.math.RSA;

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
     * @return String list of generated key parts
     */
    public String[] generate() {
        BigInteger p = primes.generate(1024);
        BigInteger q = primes.generate(1024);
        BigInteger e = primes.generate(16);

        String[] keys = rsa.generateKeys(p, q, e);

        io.writeMessage(keys[0] + "\n" + keys[2], "public_key.txt");
        io.writeMessage(keys[1] + "\n" + keys[2], ".private_key.txt");

        return keys;
    }
    
    /**
     * 
     * Encrypts given message and saves the encypted message to file
     * 
     * @param message String message to be encrypted
     * @return encrypted message as String
     */
    public String encyptMessage(String message) {
        String[] key = io.readMessage("public_key.txt").split("\n");
        String encrypted = rsa.encrypt(message, key);
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
        String[] key = io.readMessage(".private_key.txt").split("\n");
        String encypted = io.readMessage("encrypted.txt");
        return rsa.decrypt(encypted, key);
    }
}
