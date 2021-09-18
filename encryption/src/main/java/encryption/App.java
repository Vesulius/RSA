package encryption;

public class App {
    public static void main(String[] args) {
        RSA rsa = new RSA();

        rsa.generateKeys();

        // String encrypted = rsa.encrypt("message");
        // System.out.println("encrypted " + encrypted);
        // String decrypted = rsa.decrypt(encrypted);
        // System.out.println("decrypted " + decrypted);
    }
}
