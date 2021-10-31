# Manual

This program uses Gradle – a build automation tool for multi-language software development as Wikipedia descibes it.



## Gradle commands 

**Gradle commands must be run in the encryption folder**. You can use eather ./gradlew or just gradle if your computer has right version of gradle.

```$ ./gradlew tasks``` 

Displays all the runnable gradle tasks.

```$ ./gradlew test``` 

Runs all the tests

```$ ./gradlew build``` 

Assembles and tests this project. 

```$ ./gradlew run -q --console=plain ``` 

Runs this project as a JVM application. Can also be run with just ./gradlew run. Options -q and --console=plain disable unnecessary console chaff by gradle.

```$ ./gradlew checksyleMain```

Checks code against set checkstyle rules

```$ ./gradlew javadoc```

Generates javadoc in build folder


## Jar file 

Building project creates jar file. This can be run from the **RSA folder** with 

```$ java -jar encryption/build/libs/encryption.jar ```


## Program functions

The use of this program is quite simple. When ran it will explain the available commands:
* 'generate' or 'q' generates the keys needed for encryption and decryption and saves them as .txt files.
* 'write' or 'w' Asks for a message that will be encrypted and saved to encrypted.txt found in encryption/resources folder.
* 'read' or 'r' reads and decrypts the messge in encrypted.txt folder.
* 'test' or 't' crates custom tests
* 'quit' or 'q' quits the program



#### Testing

There are three different tests: timing, attempts, generation

* Timing tests test the amount of time invidual mr test takes
* attempts tests the amount of guesses required to find prime
* generation tests the amount of time prime number generation takes

The options for these are

* bytes: the size of numbers tested
* testRounds: how many times is test repeted
* savefileName: name of the file where test is saved. Leave empty for no save
* MR-rounds: how many times number is tested with MR-test
* divideByPrimes: try to divide by primes before MR-test 
