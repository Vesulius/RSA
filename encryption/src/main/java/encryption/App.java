package encryption;

public class App {
    public static void main(String[] args) {
        RSA rsa = new RSA();

        rsa.generateKeys();

        String encrypted = rsa.encrypt("This message is very big i am warning you that this message is very big i am warning you that 1234567adsfasdfasdfasdfasaasdfasdf");

        System.out.println("encrypted " + encrypted);
        String decrypted = rsa.decrypt(encrypted);
        System.out.println(decrypted);

    }
}
