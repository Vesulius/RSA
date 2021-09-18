package encryption;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FileIOTest {

    FileIO io;
    FileWriter writer;
    Scanner scanner;
    File file;

    @BeforeAll
    public void setUp() {
        io = new FileIO();
        file = new File("messages" + File.separator + "test_message.txt");
        try {
            scanner = new Scanner(file);
            writer = new FileWriter(file);

        } catch (IOException e) {
            System.out.println("An error occurred");
            e.printStackTrace();
        }
    }

    @AfterEach
    public void setAfterEach() {
        file.delete();
        try {
            file.createNewFile();
        } catch (IOException e) {
            System.out.println("An error occurred");
            e.printStackTrace();
        }
        
    }

    @AfterAll
    public void deleteTestFile() {
        file.delete();
    }

    @Test
    public void canReadFile() {
        try {
            writer.write("This is test");
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred");
            e.printStackTrace();
        }
        String message = io.readMessage("test_message.txt");
        assertEquals("This is test", message);
    }

    @Test
    public void canWriteToFile() {
        io.writeMessage("This is test", "test_message.txt");
        String content = scanner.nextLine();
        scanner.close();
        assertEquals("This is test", content);
    }

    @Test
    public void writingReplacesOldText() {
        try {
            writer.write("Old text stuff");
        } catch (IOException e) {
            System.out.println("An error occurred");
            e.printStackTrace();
        }

        io.writeMessage("This is test", "test_message.txt");
        
        String content = "";
        while (scanner.hasNextLine()) {
            String data = scanner.nextLine();
            content = content + data;
        }

        assertEquals("This is test", content);
    }
}
