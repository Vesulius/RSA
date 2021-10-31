# Implementation

Project stucture, RSA workings and test implementation. To see actual performance testing results see performance report.

The project is written entirely with Java and uses Gradle for build automation. The ui consists of console intefrace where users can generate keys, encrypt/decrypt messages and write custom performance tests for the prime generation.

BigInteger class is used in both prime generation and endcyption/decryption. RSA algorithm needs upto 2048 byte size numbers wich are much too big for more common integer and long type values. BigInteger class also has several useful methods like modulus and greates common divisor that are needed in RSA algorithm. 


## Project structure

The project has three packages and logic class that connects them.

![UML](/documentation/pictures/uml.png "UML")

Here we can see that the packages are almost entirely seperated by Logic class. The Ui class takes the commands from user and delivers them to the logic class that then uses the utility and math package classes to perform the commands.

* UI class: takes instructions from and delivers them to the logic class
* Logic class: uses the utility and math package classes to perform the user commands
* Math package:
    * RSA class: encryption/decryption and key generation
    * Primes class: generates prime numbers
* Util package:
    * FileIO: writes and reads files
    * Testing: several tests for Prime generation
    * Timer: timer for timing tests

Full project structure can be seen with javadoc from **RSA folder** with command: 

```$ YOUR_BROWSER encryption/build/docs/javadoc/index.html ```

You first must create the report with gradle. [Manual](https://github.com/Vesulius/RSA/tree/master/documentation/manual.md)


## RSA- and Miller-Rabin primality algorithm

RSA algorithm is based on the difficulty of factoring prime numbers. This means that the algorithm requires large prime numbers to make factoring them difficult. Generating primes involves selecting random integer values and testing if they are prime. 

In project prime numbers are tested generated with Miller-Rabin primality test. MR-test primality test can't guarantee number is prime but was used as it is quite common and accurate algorithm to generate prime numbers. The accuracy is based on the amount of testing rounds done as the MR-test is not determenistic. The more rounds the more accurate the test is. MR-test will falsly call composite number prime with probability of 1/4^k where k is the number of testing rounds.

RSA algorithm time encryption/decryption complexity is O(1) as it only uses few set calculations on these.

MR-test time complexity is O(k log(n)^3), where k is the number of rounds of testing and n is the tested number.


## Issues

Even though the program can encrypt and decrypts files, it has quite a lot security issues and coudn't be used as an actual security implementation.

Problems with implementation
* Generated prime numbers are not guarenteed to be prime. Actual RSA implementation use some other test such as AKS in addition to MR-test
* Dosen't check prime numbers are actually big enough. Random numbers are generated from 0 to given bytesize and 2048 byte size might give 17 back.
* Generated prime numbers might be the same number witch would make factorin easy.
* For maxium security the generated numbers shoud be similar size. The implementation dosen't use this fact.
* Dosen't adress any of the multiple security weakneses listed that have been found to work on RSA listed in wikipedia

#### Sources

* https://fi.wikipedia.org/wiki/RSA
* https://www.youtube.com/watch?v=4zahvcJ9glg
* https://www.di-mgt.com.au/rsa_alg.html
* https://cybersecuritybase.mooc.fi/module-4.3/3-asymmetric
* https://en.wikipedia.org/wiki/Miller%E2%80%93Rabin_primality_test#Complexity
* https://en.wikipedia.org/wiki/Prime_number_theorem