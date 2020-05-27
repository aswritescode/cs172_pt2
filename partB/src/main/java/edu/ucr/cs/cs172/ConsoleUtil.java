package edu.ucr.cs.cs172;

public class ConsoleUtil {

    public static boolean DEBUG_ENABLED = true;

    public static void log(String message) {
        System.out.println("[INFO]: " + message);
    }

    public static void error(String message) {
        System.out.println("[ERROR]: " + message);
    }

    public static void debug(String message) {
        if(DEBUG_ENABLED) System.out.println("[DEBUG]: " + message);
    }

    public static void debug(String message, boolean alwaysPrint) {
        if(DEBUG_ENABLED || alwaysPrint) System.out.println("[DEBUG]: " + message);
    }
}
