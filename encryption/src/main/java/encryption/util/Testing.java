package encryption.util;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Arrays;

import encryption.math.Primes;

/**
 * 
 * Tests several aspects of Miller-Rabin primality test for timing and variance
 * 
 */
public class Testing {

    FileIO io;
    Primes primes;
    Timer timer;
    SecureRandom rand;


    public Testing(FileIO io, Primes primes) {
        this.io = io;
        this.primes = primes;
        this.timer = new Timer();
        this.rand = new SecureRandom();
    }

    /**
     * 
     * Tests Miller-Rabin primality test timing
     * 
     * <p>
     * Timing for both primes and composites are tested seperately<br>
     * Test creates list of primes and list of nonprimes size of given rounds and tests the time to calculate 
     * the mr-test for each value
     * </p>
     * 
     * @param bits integer bits size of tested values
     * @param rounds integer rounds of testing different values
     * @param save String file name if you want to save invidual results
     */
    public void mrTestTiming(int bits, int testRounds, String save) {
        BigInteger[] primesList = new BigInteger[testRounds];
        BigInteger[] compositeList = new BigInteger[testRounds];

        double[] primesResults = new double[testRounds];
        double[] compositeResults = new double[testRounds];
        
        // fill both lists beforehand for more accurate timing
        for (int i = 0; i < testRounds; i++) {
            primesList[i] = BigInteger.probablePrime(bits, rand);
            do {
                compositeList[i] = new BigInteger(bits, rand);
            } while (primes.mrTest(compositeList[i], rand));
        }

        double evaluatingPrimes = 0;
        double evaluatingComposites = 0;

        timer.start();
        for (int i = 0; i < testRounds; i++) {
            if (primes.mrTest(primesList[1], rand)) evaluatingPrimes++;
            primesResults[i] = timer.lap();
        }
        double averagePrime = timer.stop();
        System.out.println("Average time of " + testRounds + " rounds to test prime number: " + averagePrime);

        timer.start();
        for (int i = 0; i < testRounds; i++) {
            if (!primes.mrTest(compositeList[1], rand)) evaluatingComposites++;
            compositeResults[i] = timer.lap();
        }
        double averageComposite = timer.stop();
        System.out.println("Average time of " + testRounds + " rounds to test composite number: " + averageComposite);

        // save invidual results to a file incase save file specified
        if (!save.isEmpty()) {
            String saved = "mr-test:\n"
                + "bits: " + bits + " rounds: " + testRounds + " results in ms"
                + "\nAverage time for primes: " + averagePrime + " prime accuracy: " + evaluatingPrimes / testRounds + " all data:\n"
                + Arrays.toString(primesResults)
                + "\nAverage time for composites:" + averageComposite + " compostie accuracy: " + evaluatingComposites / testRounds + " all data\n"
                + Arrays.toString(compositeResults);
            io.writeMessage(saved, save + ".txt");
        }
    }
  
    
    
    /**
     * 
     * Gives the average number of attempts to randomly guess a prime number
     * 
     * <p>
     * Randomly guesses prime numbers from given bitsize range until one passes
     * given rounds of Miller-Rabin-primality test.
     * 
     * can save the invidual results in file
     * </p>
     * 
     * @param bits   integer bite size of tested BigInteger
     * @param testRounds integer rounds of miller-rabin-tests
     * @param mrRounds integer rounds of testing
     * @param save String file name if you want to save invidual results
     * @return double 
     */
    public void averageNumberOfAttempts(int bits, int testRounds, String save, int mrRounds) {
        double sum = 0;
        int[] results = new int[testRounds];
        for (int i = 0; i < testRounds; i++) {
            int attempts = numberOfAttempts(bits, mrRounds);
            results[i] = attempts;
            sum += attempts;
        }

        // save invidual results to a file incase save file specified
        if (!save.isEmpty()) {
            String saved = "numberOfAttempts:\n"
                + "bits: " + bits + " testRounds: " + testRounds + " mrRounds: " + mrRounds + "\n"
                + Arrays.toString(results);
            io.writeMessage(saved, save + ".txt");
        }

        System.out.println("Average number of attempts: " +  (sum / testRounds));
    }
    
    private int numberOfAttempts(int bits, int rounds) {
        SecureRandom random = new SecureRandom();
        int attempts = 0;
        while (true) {
            attempts++;
            BigInteger prime = new BigInteger(bits, random);
            if (!primes.divideByPrimes(prime)) {
                continue;
            }
            boolean pass = true;
            for (int i = 0; i < rounds; i++) {
                if (!primes.mrTest(prime, random)) {
                    pass = false;
                    continue;
                }
            }
            if (pass) return attempts;
        }
    }

    /**
     * 
     * Tests average time to generate prime number
     * 
     * <p>
     * Repeatedly generates primes by given premises until returning average time. <br>
     * Prime generation is inherently very random and higher the testItereations the more accurate result.
     * </p>
     * 
     * @param bits int size of genereated primes
     * @param testRounds int amount of repeted tests
     * @param mrRounds int rounds of checking Miller-Rabin primality test
     * @param divideByPrimes boolean if divideByPrimes is used to quicken the results
     * @return double average generation time in milliseconds.
     */
    public void primeGeneration(int bits, int testRounds, String save, int mrRounds, boolean divideByPrimes) {
        double[] results = new double[testRounds];
        timer.start();
        for (int i = 0; i < testRounds; i++) {
            primes.generate(bits, mrRounds, divideByPrimes);
            results[i] = timer.lap();
        }
        double average = timer.stop();
        System.out.println("Average time to generate: " + average);

        // save invidual results to a file incase save file specified
        if (!save.isEmpty()) {
            String saved = "prime generation:\n" 
                + "bits: " + bits + " testRounds: " + testRounds + " mrRounds: " + mrRounds + " divideByPrimes: " + divideByPrimes
                + "\nresults in ms\n" 
                + "average: " + average + " ms\n"
                + Arrays.toString(results);
            io.writeMessage(saved, save + ".txt");
        }
    }
}
