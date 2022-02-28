package ua.demirug.geoservice;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Log {

    public static final int LEVEL_INFO = 0;
    public static final int LEVEL_WARNING = 1;
    public static final int LEVEL_ERROR = 2;

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("[HH:mm:ss dd/M/yyyy]");

    public static void info(String msg) {
        Log.print(msg, 0);
    }

    public static void warn(String msg) {
        Log.print(msg, 1);
    }

    public static void error(String msg) {
        Log.print(msg, 2);
    }

    private static void print(String msg, int loglevel) {
        StringBuilder sb = new StringBuilder(dateFormat.format(new Date()));
        switch (loglevel) {
            case LEVEL_WARNING -> sb.append("\033[0;33m"); //Append yellow color to console
            case LEVEL_ERROR -> sb.append("\033[0;31m"); //Append Red color to console
        }

        sb.append(" ").append(msg);
        sb.append("\033[0m"); // Reset color
        System.out.println(sb);

    }
}
