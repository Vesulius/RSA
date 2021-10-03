package encryption;

public class UI {

    Logic logic;

    public UI(Logic logic) {
        this.logic = logic;
    }

    public void start() {
        System.out.println("Hello, this is RSA file encryption servece");
        System.out.println("\n– – –\n");
        while (true) {
            instuctions();
            String task  = System.console().readLine();
            System.out.println("You wrote command '" + task + "' ");
            if (task.equals("generate") || task.equals("g")) {
                logic.generate();
            } else if (task.equals("write") || task.equals("w")) {
                writeMessage();
            } else if (task.equals("read") || task.equals("r")) {
                readMessage();
            } else if (task.equals("quit") || task.equals("q")) {
                System.out.println("Goodbye!");
                break;
            } else {
                System.out.println("Bad input");
            } 
            System.out.println("\n– – –\n");
        }
    }

    private void writeMessage() {
        System.out.println("Write message that will be encrypted");
        String message = System.console().readLine();
        String encrypted = logic.encyptMessage(message);
        System.out.println("Encypted message reads: " + encrypted);
    }

    private void readMessage() {
        String decrypted = logic.decryptMessage();
        System.out.println("Decrypted message reads: " + decrypted);
    }

    private void instuctions() {
        System.out.println("Commands:");
        System.out.println("To generate keys, write 'generate'");
        System.out.println("To encypt message to file, write 'write'");
        System.out.println("To translate encypted message from file, write 'read'");
        System.out.println("To quit, write 'quit'");
        System.out.println("\n– – –");
    }
}
