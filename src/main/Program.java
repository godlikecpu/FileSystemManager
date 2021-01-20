package main;

import main.file.FileInfo;
import main.file.FileRead;
import main.file.FileType;

public class Program {

    public static void main(String[] args) {

        System.out.println(FileRead.getInstance().getAmountOfLinesFromTxt(
                FileRead.getInstance().getFilesByFileType(FileType.txt, FileRead.getInstance().getFiles()).get(0)));
    }

}
