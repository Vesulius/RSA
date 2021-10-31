# Project definition

The aim of my project is to implement RSA public-key cryptosystem. The end goal is a program that can generate public and private keys and then use these to encrypt and decrypt text. The goal is to create secure way to scramble arbitary lenght text files and then decrypt them. 

The language used is Java and both the code and the documentation will be written in english. I chose java as I have the most experience with it. For the peer review english and finnish are both fine and for the computer language java is preferable but python is ok.

I'm in the bach­el­or's pro­gramme in computer science (TKT). 

## Algorithm

Initially I plan on impementing a very simple version of RSA encyption as described in https://www.di-mgt.com.au/rsa_alg.html.
This first version will use small prime numbers and will use smaller numbers and as such will not be very effective encryption. 

The key generation will use three algorithms for the following tasks:
* Algorithm for finding big primes
* Algorithm for finding coprimes
* Extended Euclidian algorithm

The encryption and decrytion is quite simple after the keys have been generated.

The aim is also to test the security of the RSA and possibly compare it to other popular available encryption algorithms.

## Interface

Interacting with the program will happen through simple console interface with some ascii graphics as it will save time and the rsa algorithm is not very visual.

#### Sources

* https://fi.wikipedia.org/wiki/RSA
* https://www.youtube.com/watch?v=4zahvcJ9glg
* https://www.di-mgt.com.au/rsa_alg.html
* https://cybersecuritybase.mooc.fi/module-4.3/3-asymmetric

