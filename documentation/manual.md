## Manual

This program uses Gradle – a build automation tool for multi-language software development as Wikipedia descibes it.

#### Gradle commands 

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

#### Program functions

The use of this program is quite simple. When ran it will explain the available commands:
* 'generate' or 'q' generates the keys needed for encryption and decryption and saves them as .txt files.
* 'write' or 'w' Asks for a message that will be encrypted and saved to encrypted.txt found in encryption/resources folder.
* 'read' or 'r' reads and decrypts the messge in encrypted.txt folder.
* 'quit' or 'q' quits the program

