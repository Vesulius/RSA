package encryption;

import java.math.BigInteger;
import java.security.SecureRandom;

import org.checkerframework.checker.units.qual.g;

/**
 * 
 * Generateing probable prime numbers
 * 
 * Generating happens by generating random numbers until one passes the primality test
 * 
 */
public class Primes {

    // all primes between 2 and 100
    int[] intPrimes = { 2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97 };
    BigInteger[] bigIntPrimes; 

    /**
     * 
     * Generates numbers until one passes the primality test
     * 
     * @param Integer bitsize of the starting seed
     * 
     * @return BigInteger number that has passed the test
     * @return if no number passess, null
     */
    public BigInteger generate(int bits) {
        SecureRandom random = new SecureRandom();
        boolean divideByPrimes = true;
        int rounds = 10;

        while (true) {
            BigInteger prime = new BigInteger(bits, random);
            if (divideByPrimes && !divideByPrimes(prime)) continue;

            boolean pass = true;
            for (int i = 0; i < rounds; i++) {
                if (!mrTest(prime, random)) {
                    pass = false;
                    continue;
                }
            }
            if (pass) return prime;
        }
    }

    /**
     * 
     * Quick test to rule out composite (non-prime) numbers
     * 
     * <p>
     * This is done dividing given number by primes from 2 to 100. If given number can be factored, it is not prime.
     * This test cannot rule numbers as prime.
     * </p>
     * 
     * @param number BigInteger number to be tested
     * @return boolean value. True if number isnt factored, false otherwise.
     */
    public boolean divideByPrimes(BigInteger number) {
        if (bigIntPrimes == null) {
            bigIntPrimes = new BigInteger[intPrimes.length];
            for (int i = 0; i < intPrimes.length; i++) {
                bigIntPrimes[i] = BigInteger.valueOf(intPrimes[i]);
            }
        }

        for (int i = 0; i < bigIntPrimes.length; i++) {
            if (number.mod(bigIntPrimes[i]).compareTo(BigInteger.ZERO) == 0) return false;
        }
        return true;
    }

    /**
     * 
     * Tests given number for primality
     * 
     * <p>
     * This is done useing Miller-Rabin primality test. Numbers that pass this test are
     * propable primes, not quaranteed.
     * </P>
     * 
     * 
     * @param prime BigInteger number to be tested for primality
     * @return boolean value. True if test is passed, false otherwise
     */
    // This is Miller-Rabin primality test for finding propable primes
    public boolean mrTest(BigInteger number, SecureRandom random) {
        // step 1: find the terms that make 2^r * d + 1 = given number
        BigInteger[] factors = factor(number);
        BigInteger r = factors[0];
        BigInteger d = factors[1];

        // step 2: select a within in the range [1, n-2]
        // The test becomes more accurate the more different a values are tried
        BigInteger a = BigInteger.ZERO;
        while(a.compareTo(BigInteger.ONE) <= 0) {
            a = new BigInteger(number.bitLength() - 1, random);
        }
        System.out.println(a.toString());

        // step 3: first count x
        BigInteger x = a.modPow(d, number);
        // if x = 1 or -1 then given number is propably truly prime
        if (x.intValue() == 1 || x.intValue() == -1) return true;

        // continue this prosess but now only if x = 1 is given prime propably truly prime
        for (int i = 0; i < r.intValueExact(); i++) {
            x = x.modPow(BigInteger.TWO, number);
            if (x.compareTo(BigInteger.ONE) == 0) return true;
        }

        return false;
    }

    /**
     * 
     * Finds terms r and d from 2^r * d + 1 = given number
     * 
     * <p>
     * Terms are found by dividing number by two until remainder is cannot be divided 
     * </p>
     * 
     * @param number to be factored
     * @return terms as Biginteger array
     */
    // find terms r and d from 2^r * d + 1 = given number
    public BigInteger[] factor(BigInteger number) {
        int r = 0;
        number = number.subtract(BigInteger.ONE);
        while (number.mod(BigInteger.TWO).compareTo(BigInteger.ZERO) == 0) {
            number = number.divide(BigInteger.TWO);
            r++;
        }

        BigInteger[] factors = new BigInteger[2];
        factors[0] = new BigInteger(String.valueOf(r));
        factors[1] = number;
        return factors;
    }
}
