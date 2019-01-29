package org.softuni.javache.util;

public class LoggingService {

    private LoggingService(){}

    public static void info(String content){
        System.out.println("\u001B[36m" + "[INFO] " + content);
    }

    public static void warning(String content){
        System.out.println("\u001B[33m" + "[WARNING] " + content);
    }

    public static void error(String content){
        System.out.println("\u001B[31m" + "[ERROR] " + content);
    }
}
