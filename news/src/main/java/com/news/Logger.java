package com.news;

public class Logger {
    public static void log(Object o) {
        System.out.println(o.toString());
    }

    public static void logWarning(Object o) {
        System.out.println("Warning: " + o.toString());
    }

    public static void logError(Object o) {
        System.out.println("Error: " + o.toString());
    }

}
