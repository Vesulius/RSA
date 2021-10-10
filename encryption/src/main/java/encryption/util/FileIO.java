package encryption.util;

import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


/**
 * 
 * A class to handle writing to and reading from files.
 * 
 * All files are stored in messages folder and this location can't be changed
 * Methods in this class can only read and write String type.
 * 
 */
public class FileIO {
    // absolutePath is currently not working
    private final String absolutePath = "C://home//vesalouh//Documents//koulu//tiralabra//RSA//encryption//messages";
    private final String relativePath = "messages";

    /**
     * 
     * This method reads from given file and returns the content
     * 
     * @param file filename in String
     * @return Text from the file as one String
     */
    public String readMessage(String file) {
        return fileReader(pathMaker(file));
    }

    /**
     * 
     * This method writes given message to given file
     * 
     * @param message message content in String
     * @param file filename in String
     * @return boolean value depending if message is written
     */
    public boolean writeMessage(String message, String file) {
        return fileWriter(message, pathMaker(file));
    }

    private String pathMaker(String file) {
        return relativePath + File.separator + file;
    }

    private String fileReader(String filePath) {
        String fileContent = "";
        try {
            File file = new File(filePath);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String data = scanner.nextLine();
                fileContent = fileContent + data;
            }
            scanner.close();
        } catch (IOException ioException) {
            System.out.println("An error occurred");
            ioException.printStackTrace();
        }

        return fileContent;
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
