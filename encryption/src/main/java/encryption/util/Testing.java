package encryption.util;

import encryption.math.Primes;

public class Testing {

    FileIO io = new FileIO();
    Primes primes = new Primes();


    public Testing(FileIO io, Primes primes) {
        this.io = io;
        this.primes = primes;
    }

    /**
     * 
     * Tests Miller-Rabin primality test with different settings
     * 
     * <p>
     * Uses multiple different inputs for mr-test and prints chart with average time in milliseconds
     * </p>
     * 
     */

    public void test() {
        // testing 1024 bit prime number generation
        // 1024 bit are used int the program for key generation

        // Lets count the averages of 100 tests as there is a lot of randomness in prime
        // generation

        int differendSizes = 5;
        int differendrounds = 3;

        Double[][] results = new Double[differendrounds][differendSizes];

        // lets test different size prime generation
        // lets test 128, 256, 512 1024 and 2048 bit primes 

        for (int y = 0; y < differendrounds; y++) {
            for (int x = 0; x < differendSizes; x++) {
                int size = (int) Math.pow(2.0, 1.0 * (x + 7));
                int rounds = y + 1;
                results[y][x] = mrTestRounds(size, rounds, 10, true);
            }
        }

        System.out.println("Chart for average ms for generaiting prime with following settings");
        System.out.println("Y-axel for different Miller-Rabin test rounds: 1, 2 and 3 rounds");
        System.out.println("X-axel for different bit sizes: 128, 256, 512 1024 and 2048 bits\n");
        for (int i = 0; i < results.length; i++) {
            for (int j = 0; j < results[0].length; j++) {
                System.out.print(results[i][j] + " ");
            }
            System.out.println();
        }
    }

    /**
     * 
     * Tests average time to generate prime number
     * 
     * <p>
     * Repeatedly generates primes by given premises until returning average time.
     * Prime generation is inherently very random and higher the testItereations the more accurate result.
     * </p>
     * 
     * @param byteSize int size of genereated primes
     * @param rmRounds int rounds of checking Miller-Rabin primality test
     * @param testIterations int amount of repeted tests
     * @param divideByPrimes boolean if divideByPrimes is used to quicken the results
     * @return double average generation time in milliseconds.
     */
    public double mrTestRounds(int byteSize, int rmRounds, int testIterations, boolean divideByPrimes) {
        long sum = 0;
        for (int i = 0; i < testIterations; i++) {
            long start = System.currentTimeMillis();
            primes.generate(byteSize, rmRounds, divideByPrimes);
            long end = System.currentTimeMillis();
            sum += end - start;
        }
        double average = (1.0 * sum) / testIterations;
        return average;
    }
}
