package main.file;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileWrite {

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

    public void writeToFile(String message) {
        createLogFile();
        try {
            FileWriter myWriter = new FileWriter("src/main/logging/logs.txt");
            myWriter.write(message);
            myWriter.close();
            System.out.println("Successfully logged in logs.txt");
        } catch (IOException e) {
            System.out.println("Could not write to log");
            e.printStackTrace();
        }
    }

}
