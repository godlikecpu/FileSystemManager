package main.gui;

import java.util.Scanner;

public class TextGUI {

    Scanner scanner = new Scanner(System.in);

    public void startGUI() {
        System.out.println("File System Manager v1.0. Please choose a task by entering the number associated:");
        System.out.println("1: List all files");
        System.out.println("0: Exit");
    }
}
