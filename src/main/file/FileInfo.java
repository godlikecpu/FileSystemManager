package main.file;

import java.io.File;

public class FileInfo extends File {

    private String path;
    private int fileSize;
    private FileType fileType;

    public FileInfo(String path, int fileSize, FileType fileType) {
        super(path);
        this.setPath(path);
        this.setFileSize(fileSize);
        this.setFileType(fileType);
    }

    public FileType getFileType() {
        return fileType;
    }

    public void setFileType(FileType fileType) {
        this.fileType = fileType;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getFileSize() {
        return fileSize;
    }

    public void setFileSize(int fileSize) {
        this.fileSize = fileSize;
    }

}
