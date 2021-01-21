package main;

import main.file.FileInfo;
import main.file.FileRead;
import main.file.FileType;
import main.file.FileHelper;
import main.logging.Logger;

public class Program {

    public static void main(String[] args) {
        FileHelper fh = new FileHelper();
        System.out.println(fh.countOfWord(
                FileRead.getInstance().getFilesByFileType(FileType.txt, FileRead.getInstance().getFiles()).get(0),
                "SToker"));
        Logger lg = new Logger();
    }
}
