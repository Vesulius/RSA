package encryption;


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
        Logic logic = new Logic(rsa, primes, io);
        UI ui = new UI(logic);
        ui.start();
    }
}
