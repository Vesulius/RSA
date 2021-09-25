package encryption;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigInteger;
import java.util.Random;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.TestInstance;

@TestInstance(Lifecycle.PER_CLASS)
public class PrimesTest {
    Primes primes;

    @BeforeAll
    public void setUp() {
        primes = new Primes();
    }

    @Test
    public void factorTermsFormGivenNumber() {
        for (int i = 5; i < 1000; i *= 7) {
            BigInteger bigI = new BigInteger(String.valueOf(i));
            BigInteger[] terms = primes.factor(bigI);
            BigInteger formulaI = BigInteger.TWO.modPow(terms[1], bigI);
            assertTrue(bigI.compareTo(formulaI) == 1);
        }
    }

    @Test
    public void checkIfPrime() {
        BigInteger prime = BigInteger.probablePrime(32, new Random());
        assertTrue(primes.mrTest(prime));
    }
}
