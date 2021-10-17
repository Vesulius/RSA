package encryption.util;

import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;


/**
 * 
 * A class to handle writing to and reading from files.
 * 
 * All files are stored in messages folder and this location can't be changed
 * Methods in this class can only read and write String type.
 * 
 */
public class FileIO {
    private final String relativePath = "resources";

    /**
     * 
     * Reads from given file and returns the content
     * 
     * @param file filename in String
     * @return Text from the file as one String
     */
    public String readMessage(String file) {
        return fileReader(pathMaker(file));
    }

    /**
     * 
     * Writes given message to given file
     * 
     * @param message message content in String
     * @param file filename in String
     * @return boolean value depending if message is written
     */
    public boolean writeMessage(String message, String file) {
        createFile(pathMaker(file));
        return fileWriter(message, pathMaker(file));
    }

    /**
     * 
     * Creates file named after given name in folder
     * 
     * @param fileName String name of the file to be created
     */
    private void createFile(String fileName) {
        try {
            // creates resources directory if it dosent exist
            Path path = FileSystems.getDefault().getPath(relativePath);
            Files.createDirectories(path);

            File file = new File(fileName);
            if (file.createNewFile()) {
                System.out.println("File created");
            } else {
                System.out.println("File already existed");
            }
        } catch (IOException ioException) {
            System.out.println("An error occurred");
            ioException.printStackTrace();
        }
    }

    private String pathMaker(String file) {
        return relativePath + File.separator + file;
    }

    private String fileReader(String filePath) {
        String fileContent = "";
        try {
            File file = new File(filePath);
            file.createNewFile();
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String data = scanner.nextLine();
                fileContent = fileContent + "\n" + data;
            }
            scanner.close();
        } catch (IOException ioException) {
            System.out.println("An error occurred");
            ioException.printStackTrace();
        }

        return fileContent.trim();
    }

    private boolean fileWriter(String content, String filePath) {
        try {
            FileWriter writer = new FileWriter(filePath);
            writer.write(content);
            writer.close();
            return true;
        } catch (IOException e) {
            System.out.println("An error occurred");
            e.printStackTrace();
        }
        return false;
    }
}
