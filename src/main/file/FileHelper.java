package main.file;

import java.io.IOException;

public class FileHelper {

    public String countOfWord(FileInfo fileInfo, String word) {
        int count = 0;
        try {
            String text = FileRead.getInstance().readFileAsString(fileInfo.getAbsolutePath());
            text = text.toLowerCase();
            word = word.toLowerCase();
            while (text.contains(word)) {
                count++;
                text = text.substring(text.indexOf(word) + word.length());
            }
        } catch (IOException ex) {
            System.out.println("Erorr while counting word: " + ex);
        }

        return "Found word: " + word + " " + count + " times";
    }

}
