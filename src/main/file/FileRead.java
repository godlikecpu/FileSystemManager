package main.file;

import java.io.*;
import java.util.ArrayList;
import java.nio.file.*;
import main.logging.Logger;

public class FileRead {

    private static FileRead instance = null;
    Logger logger = Logger.getInstance();

    public static FileRead getInstance() {
        if (instance == null)
            instance = new FileRead();

        return instance;
    }

    /**
     * Gets the size of a file in bytes by counting one at a time, logs the result
     * to logs.txt
     * 
     * @param fileInfo The file to count
     * @return The result in different forms along with execution time
     */

    public String getFileSize(FileInfo fileInfo) {
        Long startTime = logger.getCurrentTimeMs();
        int byteCount = 0;
        try (FileInputStream fileInputStream = new FileInputStream(fileInfo.getAbsolutePath())) {
            int data = fileInputStream.read();
            // -1 indicates no more data
            while (data != -1) {
                // process data
                byteCount++;
                // next data
                data = fileInputStream.read();
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        Long executionTime = logger.getExecutionTimeMs(startTime);
        String result = fileInfo.getName() + " is " + byteCount + " bytes, or " + byteCount / 1024.0 + " kilobytes ";
        logger.writeToLog(result, executionTime);
        return result + ".. Operation took " + executionTime + " ms";
    }

    /**
     * Reads a text file as a string
     * 
     * @param filePath The file to read
     * @return String containing all text
     * @throws IOException
     */

    public String readFileAsString(String filePath) throws IOException {
        return new String(Files.readAllBytes(Paths.get(filePath)));
    }

    /**
     * Gets all files from the resources folder using the Java File class
     * 
     * @return the files found
     */

    public ArrayList<FileInfo> getFiles() {
        ArrayList<FileInfo> files = new ArrayList<FileInfo>();
        File folder = new File("src/resources");
        File[] fileArray = folder.listFiles();
        for (int i = 0; i < fileArray.length; i++) {
            if (fileArray[i].isFile()) {
                FileInfo fileInfo = new FileInfo(fileArray[i].getAbsolutePath(), getFileType(fileArray[i]));
                files.add(fileInfo);
            }
        }
        return files;
    }

    /**
     * Gets the amount of lines in a text file using buffered reader to read one
     * line at a time
     * 
     * @param fileInfo The file to count
     * @return The result along with execution time
     */

    public String getAmountOfLinesFromTxt(FileInfo fileInfo) {
        Long startTime = logger.getCurrentTimeMs();
        int amount = 0;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileInfo.getAbsolutePath()))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                amount++;
            }
        } catch (Exception ex) {
            // exception code
        }
        Long executionTime = logger.getExecutionTimeMs(startTime);
        String result = "Found " + amount + " lines of text in " + fileInfo.getName();
        logger.writeToLog(result, executionTime);
        return result + " .. Operation took " + executionTime + " ms";
    }

    /**
     * Gets all files with a given FileType
     * 
     * @param fileType The given FileType
     * @param files    The ArrayList of files to search
     * @return The files with matching FileType
     */

    public ArrayList<FileInfo> getFilesByFileType(FileType fileType, ArrayList<FileInfo> files) {
        ArrayList<FileInfo> filesByType = new ArrayList<FileInfo>();
        for (FileInfo fileInfo : files) {
            if (fileInfo.getFileType() == fileType) {
                filesByType.add(fileInfo);
            }
        }
        return filesByType;
    }

    /**
     * Uses the File name to get the extension
     */

    private FileType getFileType(File file) {
        String extension = "";
        int i = file.getName().lastIndexOf('.');
        if (i > 0) {
            extension = file.getName().substring(i + 1);
        }
        return getFileTypeFromString(extension);
    }

    /**
     * Switch returning the correct FileType
     * 
     * @param extension String to match with FileType
     * @return Support FileTypes or unknown if not supported
     */

    public FileType getFileTypeFromString(String extension) {
        switch (extension) {
            case "jpg":
                return FileType.jpg;
            case "png":
                return FileType.png;
            case "jpeg":
                return FileType.jpeg;
            case "jfif":
                return FileType.jfif;
            case "txt":
                return FileType.txt;
            default:
                return FileType.unknown;
        }
    }

}
