package encryption.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import encryption.util.Logic;

public class UI {

    Logic logic;
    BufferedReader reader;

    public UI(Logic logic) {
        this.logic = logic;
    }

    public void start() {
        reader = new BufferedReader(new InputStreamReader(System.in));
        
        System.out.println("\n– – –\n");
        System.out.println("Hello, this is RSA file encryption servece");
        System.out.println("\n– – –\n");
        
        while (true) {
            instuctions();
            String task = readUserInput();
            
            // String task  = System.console().readLine();
            System.out.println("You wrote command '" + task + "'\n");
            if (task.equals("generate") || task.equals("g")) {
                String[] keys = logic.generate();
                System.out.println("New keys generated\n");
                System.out.println("PUBLIC KEY:\n");
                System.out.println(keys[0] + "\n\n" + keys[2]);
                System.out.println("\nPRIVATE KEY:\n");
                System.out.println(keys[1] + "\n\n" + keys[2]);
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

    private String readUserInput() {
        try {
            return reader.readLine();
        } catch (IOException e) {
            System.out.println("An error occurred");
            e.printStackTrace();
        }
        return "";
    }

    private void writeMessage() {
        System.out.println("Write message that will be encrypted");
        String message = readUserInput();
        
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
