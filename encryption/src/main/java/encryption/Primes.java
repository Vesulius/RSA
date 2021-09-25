package encryption;

import java.math.BigInteger;


/**
 * 
 * Generateing probable prime numbers
 * 
 * Generating happens by generating random numbers until one passes the primality test
 * 
 */
public class Primes {


    /**
     * 
     * Generates numbers until one passes the primality test
     * 
     * @return BigInteger number that has passed the test
     * @return if no number passess, null
     */
    public BigInteger generate() {
        BigInteger seed = new BigInteger("123456");
        for (int i = 1; i < 100000; i++) {
            seed = seed.add(BigInteger.ONE);
            if (mrTest(seed)) {
                System.out.println(i);
                return seed;     
            }
        }
        return null;
    }

    /**
     * 
     * Testing numbers for primality
     * 
     * <p>
     * This uses Miller-Rabin primality test. Numbers that pass this test are
     * propable primes, not quaranteed
     * </P>
     * 
     * 
     * @param prime BigInteger number to be tested for primality
     * @return boolean true value if test is passed, false otherwise
     */
    // This is Miller-Rabin primality test for finding propable primes
    public boolean mrTest(BigInteger prime) {
        // step 1: find the terms that make 2^r * d + 1 = given prime
        BigInteger[] factors = factor(prime);
        BigInteger r = factors[0];
        BigInteger d = factors[1];

        // step 2: select a within in the range [1, n-2]
        // The test becomes more accurate the more different a values are tried
        // Lets just choose 2 for now
        BigInteger a = new BigInteger("2");

        // step 3: first count x
        BigInteger x = a.modPow(d, prime);
        // if x = 1 or -1 then given prime is propably truly prime
        if (x.intValue() == 1) return true;
        if (x.intValue() == -1) return true;

        // continue this prosess but now only if x = 1 is given prime propably truly prime
        for (int i = 0; i < r.intValueExact(); i++) {
            x = x.modPow(BigInteger.TWO, prime);
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
