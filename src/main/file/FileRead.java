package main.file;

import java.io.*;
import java.util.ArrayList;

public class FileRead {

    private static FileRead instance = null;

    public static FileRead getInstance() {
        if (instance == null)
            instance = new FileRead();

        return instance;
    }

    public int getFileSize(File file) {
        int byteCount = 0;
        try (FileInputStream fileInputStream = new FileInputStream(file.getAbsolutePath())) {
            int data = fileInputStream.read();
            // -1 indicates no more data
            while (data != -1) {
                // process data
                byteCount++;
                // next data
                data = fileInputStream.read();
            }
            System.out.println(file.getName() + " is " + byteCount + " bytes, or " + byteCount / 1024.0 + " kilobytes ("
                    + (int) Math.ceil(byteCount / 1024.0) + "KB on disk).");
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return byteCount;
    }

    public ArrayList<FileInfo> getFiles() {
        ArrayList<FileInfo> files = new ArrayList<FileInfo>();
        File folder = new File("src/resources");
        File[] listOfFiles = folder.listFiles();
        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile()) {
                File file = listOfFiles[i];
                FileInfo fileInfo = new FileInfo(listOfFiles[i].getAbsolutePath(), getFileSize(file),
                        getFileType(file));
                files.add(fileInfo);
            }
        }
        return files;
    }

    public int getAmountOfLinesFromTxt(FileInfo fileInfo) {
        int amount = 0;
        if (fileInfo.getFileType() == FileType.txt) {
            try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileInfo.getPath()))) {
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    amount++;
                }
            } catch (Exception ex) {
                // exception code
            }
        }
        return amount;
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
