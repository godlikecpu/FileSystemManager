package main.file;

import java.io.File;

public class FileInfo extends File {
    /**
     * This class is a wrapper for the Java File class, with a custom FileType
     * attribute
     */

    private FileType fileType;

    public FileInfo(String path, FileType fileType) {
        super(path);
        this.setFileType(fileType);
    }

    public FileType getFileType() {
        return fileType;
    }

    public void setFileType(FileType fileType) {
        this.fileType = fileType;
    }
}
