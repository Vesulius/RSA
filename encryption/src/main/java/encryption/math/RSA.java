package encryption.math;

import java.math.BigInteger;

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

    /**
     * 
     * Key generator
     * 
     * <p>
     * Stores keys as BigInteger arrays
     * </p>
     * 
     * @param p BigInteger primes for calculations
     * @param q BigInteger primes for calculations
     * @param e BigInteger primes for calculations
     * @return String array with lenght 3 for all keys in bytes
     * 
     */
    public String[] generateKeys(BigInteger p, BigInteger q, BigInteger e) {
        // Random random = new Random();
        BigInteger one = BigInteger.ONE;

        // p and q must be primes

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

        // (d * e) % c = 1
        // Can be calculated using Extended Euclidean algorithm:
        // https://en.wikipedia.org/wiki/Extended_Euclidean_algorithm
        BigInteger d = e.modInverse(c);
        

        // integers for encryption: e, n
        // integers for decryption: d, n
        
        String[] keys = new String[3];

        keys[0] = e.toString();
        keys[1] = d.toString();
        keys[2] = n.toString();

        return keys;
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
    public String encrypt(String message, String[] publicKey) {
        BigInteger e = new BigInteger(publicKey[0]);
        BigInteger n = new BigInteger(publicKey[1]);

        byte[] byteMessage = message.getBytes();
        BigInteger encyptedMessage = new BigInteger(byteMessage).modPow(e, n);
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
    public String decrypt(String encryptedMessage, String[] privateKey) {
        BigInteger d = new BigInteger(privateKey[0]);
        BigInteger n = new BigInteger(privateKey[1]);

        BigInteger message = new BigInteger(encryptedMessage);
        BigInteger decryptedMessage = message.modPow(d, n);
        return new String(decryptedMessage.toByteArray());
    }
}
