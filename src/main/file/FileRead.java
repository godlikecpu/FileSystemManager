package main.file;

import java.io.*;
import java.util.ArrayList;
import java.nio.file.*;

public class FileRead {

    private static FileRead instance = null;

    public static FileRead getInstance() {
        if (instance == null)
            instance = new FileRead();

        return instance;
    }

    public String getFileSize(FileInfo fileInfo) {
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
        return fileInfo.getName() + " is " + byteCount + " bytes, or " + byteCount / 1024.0 + " kilobytes ("
                + (int) Math.ceil(byteCount / 1024.0) + "KB on disk).";
    }

    public String readFileAsString(String filePath) throws IOException {
        return new String(Files.readAllBytes(Paths.get(filePath)));
    }

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

    public String getAmountOfLinesFromTxt(FileInfo fileInfo) {
        int amount = 0;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileInfo.getAbsolutePath()))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                amount++;
            }
        } catch (Exception ex) {
            // exception code
        }
        return "Found " + amount + " lines of text in " + fileInfo.getName();
    }

    public ArrayList<FileInfo> getFilesByFileType(FileType fileType, ArrayList<FileInfo> files) {
        ArrayList<FileInfo> filesByType = new ArrayList<FileInfo>();
        for (FileInfo fileInfo : files) {
            if (fileInfo.getFileType() == fileType) {
                filesByType.add(fileInfo);
            }
        }
        return filesByType;
    }

    private FileType getFileType(File file) {
        String extension = "";
        int i = file.getName().lastIndexOf('.');
        if (i > 0) {
            extension = file.getName().substring(i + 1);
        }
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
