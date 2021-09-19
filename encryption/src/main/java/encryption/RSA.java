package encryption;

import java.math.BigInteger;
import java.util.Random;


/**
 * 
 * Class that is responsible for the encryption mechanism
 * 
 * Generates and currently stores keys
 * 
 * Encrypts and decrypts String text
 * 
 */
public class RSA {

    BigInteger[] publicKey = new BigInteger[2];
    BigInteger[] privateKey = new BigInteger[2];

    /**
     * 
     * Key generator
     * 
     * <p>
     * Stores keys as BigInteger arrays
     * </p>
     * 
     */
    public void generateKeys() {
        Random random = new Random();
        BigInteger one = new BigInteger("1");

        // p and q must be primes
        BigInteger p = BigInteger.probablePrime(512, random);
        BigInteger q = BigInteger.probablePrime(512, random);

        BigInteger n = p.multiply(q);

        // phi(n) = c
        // phi is Euler's totient function
        // phi(n) is the number of integers from 1 to n that coprime with n
        BigInteger c = p.subtract(one).multiply(q.subtract(one));

        // 2 conditions for e:
        // 1: 1 < e < c
        // 2: coprime with c
        // multiple algorithms to solve this:
        // https://en.wikipedia.org/wiki/Greatest_common_divisor#Calculation
        // this also works: just chose a prime between 1 and c
        BigInteger e = BigInteger.probablePrime(32, random);
        System.out.println("e: " + e.toString() + "\n");

        // (d * e) % c = 1
        // Can be calculated using Extended Euclidean algorithm:
        // https://en.wikipedia.org/wiki/Extended_Euclidean_algorithm
        BigInteger d = e.modInverse(c);
        

        // integers for encryption: e, n
        // integers for decryption: d, n
        
        this.publicKey[0] = new BigInteger(String.valueOf(e));
        this.publicKey[1] = new BigInteger(String.valueOf(n));

        this.privateKey[0] = new BigInteger(String.valueOf(d));
        this.privateKey[1] = new BigInteger(String.valueOf(n));
    }

    /**
     * 
     * Encrypts given String text and returns encrypted text as String
     * 
     * Uses publickey from this class to encrypt message
     * Maxium message size is currently 128 characters
     * 
     * @param message String text to be crypted
     * @return Encrypted text as String
     */
    public String encrypt(String message) {
        byte[] byteMessage = message.getBytes();
        BigInteger encyptedMessage = new BigInteger(byteMessage).modPow(publicKey[0], publicKey[1]);
        return encyptedMessage.toString();
    }

    /**
     * 
     * Decrypts given String text and returns decrypted text as String
     * 
     * Uses privatekey from this class to encrypt message
     * 
     * @param encryptedMessage Encrypted String message to be decrypted
     * @return Decrypted message as String
     */
    public String decrypt(String encryptedMessage) {
        BigInteger mes = new BigInteger(encryptedMessage);
        BigInteger decryptedMessage = mes.modPow(privateKey[0], privateKey[1]);
        return new String(decryptedMessage.toByteArray());
    }
}
