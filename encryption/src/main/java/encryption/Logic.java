package encryption;


import java.math.BigInteger;
import java.util.ArrayList;

import encryption.math.Primes;
import encryption.math.RSA;
import encryption.util.FileIO;
import encryption.util.Testing;

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
    private Testing test;

    public Logic(RSA rsa, Primes primes, FileIO io, Testing test) {
        this.rsa = rsa;
        this.primes = primes;
        this.io = io;
        this.test = test;
    }

    /**
     * 
     * Generates the keys needed for encyption and decryption
     * 
     * @return String list of generated key parts
     */
    public String[] generate() {
        BigInteger p = primes.generate(1024, 10, true);
        BigInteger q = primes.generate(1024, 10, true);
        BigInteger e = primes.generate(16, 10, true);

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

    /**
     * 
     * Runs custom tests for prime generation
     * 
     * <p>
     * Tests several ascpects of prime generation. See Ui class for options
     * </p>
     * 
     * @param options ArrayList<String> secifing options
     */
    public void test(ArrayList<String> options) {
        try {
            switch (options.get(0)) {
                case "timing": case "t":
                    test.mrTestTiming(Integer.valueOf(options.get(1)), Integer.valueOf(options.get(2)), options.get(3));
                    break;
                case "attempts": case "a":
                    test.averageNumberOfAttempts(Integer.valueOf(options.get(1)), Integer.valueOf(options.get(2)),
                        options.get(3), Integer.valueOf(options.get(4)));
                    break;
                case "generation": case "g":
                    test.primeGeneration(Integer.valueOf(options.get(1)), Integer.valueOf(options.get(2)),
                        options.get(3), Integer.valueOf(options.get(4)), options.get(5).equals("y"));
                    break;
                default:
                    System.out.println("Bad option");
            }
        } catch (Exception e) {
            System.out.println("Bad input");
        } 
    }
}
