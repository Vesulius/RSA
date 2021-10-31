package encryption.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import encryption.Logic;

public class UI {

    Logic logic;
    BufferedReader reader;

    public UI(Logic logic) {
        this.logic = logic;
    }

    public void start() {
        reader = new BufferedReader(new InputStreamReader(System.in));

        breakMark();
        System.out.println("Hello, this is RSA file encryption servece");
        breakMark();

        while (true) {
            instuctions();
            String task = readUserInput();

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
                ;
            } else if (task.equals("test") || task.equals("t")) {
                test();
            } else if (task.equals("quit") || task.equals("q")) {
                System.out.println("Goodbye!");
                break;
            } else {
                System.out.println("Bad input");
            }
            breakMark();
        }
    }

    private String readUserInput() {
        try {
            String userInput = reader.readLine();
            System.out.println("You wrote command '" + userInput + "'\n");
            return userInput.toLowerCase();
        } catch (IOException e) {
            System.out.println("An error occurred");
            e.printStackTrace();
        }
        return "";
    }

    private void test() {
        ArrayList<String> options = new ArrayList<>();
        System.out.println("What are you testing?");
        breakMark();
        
        System.out.println("Options: timing, attempts, generation");
        options.add(readUserInput());
        System.out.println("Give number: bytes");
        options.add(readUserInput());
        System.out.println("Give number: testRounds");
        options.add(readUserInput());
        System.out.println("Give name: savefileName. Leave blank for no save");
        options.add(readUserInput());

        if (options.get(0).equals("timing") || options.get(0).equals("t")) {
            logic.test(options);
            return;
        }
        System.out.println("Give number: mr-Rounds");
        options.add(readUserInput());
        
        if (options.get(0).equals("attempts") || options.get(0).equals("a")) {
            logic.test(options);
            return;
        }
        System.out.println("Give y/n: divideByPirmes");
        options.add(readUserInput());
        logic.test(options);
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
        System.out.println("To test prime generatinon, write 'test'");
        System.out.println("To quit, write 'quit'");
        System.out.println("\n– – –");
    }

    private void breakMark() {
        System.out.println("\n– – –\n");
    }
}
