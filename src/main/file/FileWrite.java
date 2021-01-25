package main.file;

import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;

public class FileWrite {

    public FileWrite() {
        createLogFile();
    }

    /**
     * Checks if the log file exists, and creates it if it doesnt
     */

    public void createLogFile() {
        try {
            File logs = new File("src/main/logging/logs.txt");
            if (logs.createNewFile()) {
                System.out.println("Log file created: " + logs.getName());
            } else {
                System.out.println("logs.txt already exists");
            }
        } catch (IOException e) {
            System.out.println("Could not create file");
            e.printStackTrace();
        }
    }

    /**
     * Writes to logs.txt
     * 
     * @param message the log message
     */

    public void writeToFile(String message) {
        try {
            FileWriter myWriter = new FileWriter("src/main/logging/logs.txt", true);
            BufferedWriter bw = new BufferedWriter(myWriter);
            bw.append(message);
            bw.newLine();
            bw.close();
            System.out.println("Successfully logged in logs.txt");
        } catch (IOException e) {
            System.out.println("Could not write to log");
            e.printStackTrace();
        }
    }

}
