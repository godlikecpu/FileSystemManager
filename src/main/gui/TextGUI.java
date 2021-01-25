package main.gui;

import java.util.ArrayList;
import java.util.Scanner;
import main.file.*;

public class TextGUI {

    FileRead fileRead = FileRead.getInstance();
    Scanner scanner = new Scanner(System.in);

    public void startGUI() {
        System.out.println("File System Manager v1.0");
        mainMenu();
    }

    /**
     * Main menu of the program. Input validation is done with a default case
     */

    private void mainMenu() {
        System.out.println("###################");
        System.out.println("Main menu:");
        System.out.println("1: List all files");
        System.out.println("2: List all files with extension");
        System.out.println("0: Exit");
        int selected = getMenuInput();

        switch (selected) {
            case 0:
                System.out.println("Exiting...");
                break;
            case 1:
                allFilesMenu();
                break;
            case 2:
                fileExtensionMenu();
                break;
            default:
                System.out.println("Please choose a number on the list");
                mainMenu();
        }
    }

    /**
     * Reads all files found in resources and calls the file selection menu
     */

    private void allFilesMenu() {
        System.out.println("###################");
        System.out.println("All files:");
        ArrayList<FileInfo> files = fileRead.getFiles();
        listAndSelectFile(files);
    }

    /**
     * Reads all files with a given extension found in resources and calls the file
     * selection menu
     */

    private void fileExtensionMenu() {
        ArrayList<FileInfo> files = new ArrayList<FileInfo>();
        String extension = "";
        do {
            System.out.println("Please type the wanted extension");
            extension = getTextInput();
            FileType fileType = fileRead.getFileTypeFromString(extension);
            files = fileRead.getFilesByFileType(fileType, fileRead.getFiles());
            if (files.size() == 0) {
                System.out.println("No files with given extension found");
            }
        } while (files.size() == 0);
        System.out.println("###################");
        System.out.println("Files with extension: " + extension);
        listAndSelectFile(files);

    }

    /**
     * Prints files and handles the input for file selection
     * 
     * @param files The files for the menu
     */

    private void listAndSelectFile(ArrayList<FileInfo> files) {
        int input = 0;
        do {
            for (int i = 1; i <= files.size(); i++) {
                System.out.println(i + ": " + files.get(i - 1).getName());
            }
            input = getMenuInput();

            if (input > files.size() || input < 0) {
                System.out.println("Please choose a number on the list");
            }
        } while (input == 0 || input > files.size());
        FileInfo fileChosen = files.get(input - 1);
        fileManipulationMenu(fileChosen);
    }

    /**
     * Prints the file manipulation options and handles the input for file
     * manipulation
     */

    private void fileManipulationMenu(FileInfo file) {
        System.out.println("###################");
        System.out.println("File chosen: " + file.getName());
        System.out.println("Please choose action:");
        System.out.println("1: Get file size");

        if (file.getFileType().equals(FileType.txt)) {
            System.out.println("2: Word counter");
            System.out.println("3: Line counter");
        }
        System.out.println("0: Main menu");

        int selected = getMenuInput();

        switch (selected) {
            case 1:
                System.out.println(fileRead.getFileSize(file));
                fileManipulationMenu(file);
                break;
            case 2:

                WordCounter wc = new WordCounter();
                wc.sortandSetTextWords(file);
                chooseSearchAlgorithm(file, wc);
                fileManipulationMenu(file);
                break;
            case 3:
                System.out.println(fileRead.getAmountOfLinesFromTxt(file));
                fileManipulationMenu(file);
                break;
            case 0:
                mainMenu();
                break;
            default:
                System.out.println("Please choose a number on the list");
                fileManipulationMenu(file);
        }
    }

    /**
     * Menu for choosing search algorithm
     * 
     * @param file file to search
     */

    private void chooseSearchAlgorithm(FileInfo file, WordCounter wc) {
        System.out.println("###################");
        System.out.println("Choose search algorithm");
        System.out.println("1: Binary search");
        System.out.println("2: Linear search");
        int selected = getMenuInput();

        System.out.println("Please enter a word: ");
        String word = getTextInput();

        switch (selected) {
            case 1:
                System.out.println(wc.binarySearch(file, word));
                break;
            case 2:
                System.out.println(wc.linearSearch(file, word));
                break;
            default:
                System.out.println("Please choose a number on the list");
                chooseSearchAlgorithm(file, wc);
        }
    }

    /**
     * Input for the menus with numbers
     * 
     * @return the number input in scanner
     */

    private int getMenuInput() {
        System.out.println("###################");
        System.out.println("Please choose a task: ");
        int menuInput = -1;
        do {
            String input = getTextInput();
            try {
                menuInput = Integer.parseInt(input);
            } catch (Exception error) {
                System.out.println("Please type a valid number");
            }
        } while (menuInput == -1);
        return menuInput;
    }

    /**
     * Basic scanner textinput
     * 
     * @return scanner String
     */

    private String getTextInput() {
        return scanner.nextLine();
    }

}
