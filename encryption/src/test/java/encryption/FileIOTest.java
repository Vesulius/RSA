package encryption;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import encryption.util.*;

import org.junit.jupiter.api.TestInstance;

@TestInstance(Lifecycle.PER_CLASS)
public class FileIOTest {

    FileIO io;
    FileWriter writer;
    Scanner scanner;
    File file;

    @BeforeAll
    public void setUp() throws IOException {
        io = new FileIO();
        file = new File("resources" + File.separator + "test_message.txt");

        if (!file.exists()) {
            file.createNewFile();
        }
        writer = new FileWriter(file);
    }

    @BeforeEach
    public void setWriterAndScanner() throws IOException {
        writer = new FileWriter(file);
        scanner = new Scanner(file);
    }

    @AfterEach
    public void setAfterEach() throws IOException {
        file.delete();
        file.createNewFile();
    }

    @AfterAll
    public void deleteTestFile() {
        file.delete();
    }

    @Test
    public void canReadFile() throws IOException {
        writer.write("This is test");
        writer.close();
        String message = io.readMessage("test_message.txt");
        System.out.println(message);
        assertEquals("This is test", message);
    }

    @Test
    public void canWriteToFile() throws FileNotFoundException {
        io.writeMessage("This is test", "test_message.txt");
        String content = scanner.nextLine();
        scanner.close();
        assertEquals("This is test", content);
    }

    @Test
    public void writingReplacesOldText() throws IOException {
        writer.write("Old text stuff");
        writer.close();

        io.writeMessage("This is test", "test_message.txt");

        String content = "";
        while (scanner.hasNext()) {
            String data = scanner.nextLine();
            content = content + data;
        }
        assertEquals("This is test", content);
    }

    @Test
    public void deletingFileIsDeleted() {
        io.deleteFile("test_message.txt");
        assertFalse(file.delete());
    }
}
