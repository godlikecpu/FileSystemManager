package main.logging;

import java.text.SimpleDateFormat;
import java.util.Date;
import main.file.FileWrite;

public class Logger {

    public String getFormattedDateTime() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy 'at' HH:mm:ss z");
        Date date = new Date(getCurrentTimeMs());
        String formattedDate = formatter.format(date);

        return formattedDate;
    }

    public Long getCurrentTimeMs() {
        return System.currentTimeMillis();
    }

    public void writeToLog(String logMessage) {
        FileWrite fw = new FileWrite();
        fw.writeToFile(logMessage);
    }

}