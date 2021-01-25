package main.logging;

import java.text.SimpleDateFormat;
import java.util.Date;
import main.file.FileWrite;

public class Logger {
    private static FileWrite fw = null;
    private static Logger instance = null;

    public static Logger getInstance() {
        if (instance == null) {
            instance = new Logger();

        }
        return instance;
    }

    /**
     * Current date to use in logs
     * 
     * @return a formatted date
     */

    public String getFormattedDateTime() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy 'at' HH:mm:ss z");
        Date date = new Date(getCurrentTimeMs());
        String formattedDate = formatter.format(date);

        return formattedDate;
    }

    /**
     * Wrapper method for getting the current unix timestamp in ms
     * 
     * @return current system time in ms
     */
    public Long getCurrentTimeMs() {
        return System.currentTimeMillis();
    }

    /**
     * Calculates the difference between a given start time and the current time
     * 
     * @param startTime start time
     * @return execution time in ms
     */

    public Long getExecutionTimeMs(Long startTime) {
        return System.currentTimeMillis() - startTime;
    }

    /**
     * Uses FileWrite to write logs
     */

    public void writeToLog(String result, long executionTime) {
        if (fw == null) {
            fw = new FileWrite();
        }
        fw.writeToFile(getFormattedDateTime() + " " + result + " .. Execution time: " + executionTime + "ms");
    }

}