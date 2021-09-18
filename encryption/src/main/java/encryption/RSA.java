package encryption;

import java.math.BigInteger;

public class RSA {

    BigInteger[] publicKey = new BigInteger[2];
    BigInteger[] privateKey = new BigInteger[2];

    public void generateKeys() {
        // p and q must be primes
        int p = 61;
        int q = 53;

        int n = p * q;

        // phi(n) = c
        // phi is Euler's totient function
        // phi(n) is the number of integers from 1 to n that coprime with n
        int c = (p - 1) * (q - 1);

        // 2 conditions for e:
        // 1: 1 < e < c
        // 2: coprime with n and c
        // multiple algorithms to solve this:
        // https://en.wikipedia.org/wiki/Greatest_common_divisor#Calculation
        // lets just choose 17 for now
        int e = 17;

        // (d * e) % c = 1
        // Can be calculated using Extended Euclidean algorithm:
        // https://en.wikipedia.org/wiki/Extended_Euclidean_algorithm
        // this works but is slow:
        int d = -1;
        for (int i = 0; i < c * 2; i++) {
            int remainder = (i * e) % c;
            if (remainder == 1) {
                d = i;
                break;
            }
        }
        d = 413;
        // integers for encryption: e, n
        // integers for decryption: d, n
        
        this.publicKey[0] = new BigInteger(String.valueOf(e));
        this.publicKey[1] = new BigInteger(String.valueOf(n));

        this.privateKey[0] = new BigInteger(String.valueOf(d));
        this.privateKey[1] = new BigInteger(String.valueOf(n));

        // BigInteger test = new BigInteger("65");
        // BigInteger enc = test.modPow(publicKey[0], publicKey[1]);
        // BigInteger dec = enc.modPow(privateKey[0], privateKey[1]);

        // System.out.println(enc);
        // System.out.println(dec);

        String message = "hello";

        byte[] byteMessage = message.getBytes();
        // System.out.println(byteToString(byteMessage));
        System.out.println(new String(byteMessage));
        byte[] encyptedMessage = new BigInteger(byteMessage).modPow(publicKey[0], publicKey[1]).toByteArray();
        System.out.println(new String(encyptedMessage));
        byte[] decryptedMessage = new BigInteger(encyptedMessage).modPow(privateKey[0], privateKey[1]).toByteArray();
        // System.out.println(byteToString(decryptedMessage));
        System.out.println(new String(decryptedMessage));

    }
 
    private String byteToString(byte[] stream) {
        String ret = "";
        for (byte b : stream) {
            ret += Byte.toString(b);
        }
        return ret;
    }

    public String encrypt(String message) {
        byte[] byteMessage = message.getBytes();
        System.out.println(byteToString(byteMessage));
        BigInteger encyptedMessage = new BigInteger(byteMessage).modPow(publicKey[0], publicKey[1]);
        return encyptedMessage.toString();
    }

    public String decrypt(String encryptedMessage) {
        BigInteger mes = new BigInteger(encryptedMessage);
        BigInteger decryptedMessage = mes.modPow(privateKey[0], privateKey[1]);
        System.out.println(byteToString(decryptedMessage.toByteArray()));
        return new String(decryptedMessage.toByteArray());
    }
}
