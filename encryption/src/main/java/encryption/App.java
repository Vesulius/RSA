package encryption;

import java.math.BigInteger;

public class App {
    public static void main(String[] args) {
        Primes primes = new Primes();

        BigInteger prime = primes.generate();
    }
}
