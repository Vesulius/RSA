package encryption;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Random;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import encryption.math.Primes;

import org.junit.jupiter.api.TestInstance;

@TestInstance(Lifecycle.PER_CLASS)
public class PrimesTest {
    Primes primes;
    Random rand;
    SecureRandom secRand;


    @BeforeAll
    public void setUp() {
        rand = new Random();
        secRand = new SecureRandom();
        primes = new Primes();
    }

    @Test
    public void factorTermsFormGivenNumber() {
        for (int i = 0; i < 100; i++) {
            BigInteger bigI = new BigInteger(1024, rand);
            BigInteger[] terms = primes.factor(bigI);
            BigInteger formulaI = BigInteger.TWO.modPow(terms[1], bigI);
            assertTrue(bigI.compareTo(formulaI) == 1);
        }
    }

    @Test
    public void checkIfPrime() {
        for (int i = 0; i < 100; i++) {
            BigInteger prime = BigInteger.probablePrime(32, rand);
            assertTrue(primes.mrTest(prime, secRand));
        }
    }

    @Test
    public void compositeNumbersGiveFalse() {
        for (int i = 0; i < 100; i++) {
            BigInteger composite = BigInteger.valueOf(rand.nextInt(10000) * rand.nextInt(10000));
            assertFalse(primes.mrTest(composite, secRand));
        }
    }

    @Test
    public void cantDividePrimes() {
        for (int i = 0; i < 100; i++) {
            BigInteger prime = BigInteger.probablePrime(32, rand);
            assertTrue(primes.divideByPrimes(prime));
        }
    }

    @Test
    public void givenValueIsPrime() {
        BigInteger prime = primes.generate(8, 10, true);
        for (int i = 2; i < Math.sqrt(255.0); i++) {
            assertFalse(prime.mod(new BigInteger(String.valueOf(i))).compareTo(BigInteger.ZERO) == 0);
        }
    }
}
