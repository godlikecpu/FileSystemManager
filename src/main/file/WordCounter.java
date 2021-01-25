package main.file;

import main.logging.Logger;
import java.io.IOException;
import java.util.Arrays;

public class WordCounter {

    Logger logger = Logger.getInstance();
    String[] sortedTextWords;

    /**
     * Sorts the array to use in binary search
     * 
     * @param file File to sort
     */

    public void sortandSetTextWords(FileInfo file) {
        String text = prepareString(file);
        // split words on all abnormal characters
        String[] textWords = text.split("\\W+");
        // sort the array
        Arrays.parallelSort(textWords);
        sortedTextWords = textWords;
    }

    /**
     * Binary search implementation.
     * 
     * @param fileInfo The file to search
     * @param word     The given word
     * @return Result string along with execution time
     */

    public String binarySearch(FileInfo fileInfo, String word) {
        Long startTime = logger.getCurrentTimeMs();
        word = word.toLowerCase();
        // pass true for the first occurrence
        int first = binarySearchAlgorithm(sortedTextWords, word, true);
        // pass false for the last occurrence
        int last = binarySearchAlgorithm(sortedTextWords, word, false);
        int count = 0;
        if (first != -1) {
            count = last - first + 1;
        }
        String result = "Found word: " + "\"" + word + "\"" + " " + count + " times in " + fileInfo.getName();
        Long executionTime = logger.getExecutionTimeMs(startTime);
        logger.writeToLog(result, executionTime);
        return result + ".. Operation took " + executionTime + " ms";
    }

    /**
     * Searches an array for a given word using binary search
     * 
     * @param textWords   The text to search
     * @param word        The given word
     * @param searchFirst Set to true to get the first occurence of the element, or
     *                    false to get the last
     * @return returns the index of the given word
     */

    private static int binarySearchAlgorithm(String[] textWords, String word, boolean searchFirst) {
        int left = 0;
        int right = textWords.length - 1;
        // initialize the result by -1
        int result = -1;
        // loop till the search space is exhausted
        while (left <= right) {
            // find the mid-value in the search space and compares it with the target
            int mid = (left + right) / 2;
            // if the target is found, update the result
            if (word.equals(textWords[mid])) {
                result = mid;
                // go on searching towards the left (lower indices)
                if (searchFirst) {
                    right = mid - 1;
                }
                // go on searching towards the right (higher indices)
                else {
                    left = mid + 1;
                }
            }
            // if the target is less than the middle element, discard the right half
            else if (word.compareTo(textWords[mid]) < 0) {
                right = mid - 1;
            }
            // if the target is more than the middle element, discard the left half
            else {
                left = mid + 1;
            }
        }

        // return the found index or -1 if the element is not found
        return result;
    }

    /**
     * Uses linear search for a given word
     * 
     * @param fileInfo The file to search
     * @param word     The given word
     * @return Result string along with execution time
     */

    public String linearSearch(FileInfo file, String word) {
        String text = prepareString(file);
        Long startTime = logger.getCurrentTimeMs();
        int count = 0;
        String[] textWords = text.split("\\W+");
        word = word.toLowerCase();
        for (String textWord : textWords) {
            if (textWord.equals(word)) {
                count++;
            }
        }
        Long executionTime = logger.getExecutionTimeMs(startTime);
        String result = "Found word: " + "\"" + word + "\"" + " " + count + " times in " + file.getName();
        logger.writeToLog(result, executionTime);
        return result + ".. Operation took " + executionTime + " ms";
    }

    /**
     * Uses FileRead to read the text file and convert it to a string to use for the
     * search algorithms
     */

    private String prepareString(FileInfo fileInfo) {
        String text = "";
        try {
            text = FileRead.getInstance().readFileAsString(fileInfo.getAbsolutePath());
        } catch (IOException ex) {
            System.out.println("Error while counting word: " + ex);
        }

        text = text.toLowerCase();
        return text;
    }

}
