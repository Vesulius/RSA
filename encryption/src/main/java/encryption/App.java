package encryption;

import encryption.math.Primes;
import encryption.math.RSA;
import encryption.ui.UI;
import encryption.util.*;

/**
 * 
 * Responsible for starting the application and creating the classes
 * 
 */
public class App {
    public static void main(String[] args) {
        Primes primes = new Primes();
        RSA rsa = new RSA();
        FileIO io = new FileIO();
        Testing test = new Testing(io, primes);
        Logic logic = new Logic(rsa, primes, io, test);
        UI ui = new UI(logic);
        ui.start();
    }
}
