package com.kodilla.integrationexercise;

import java.util.ArrayList;
import java.util.List;

public class FileNamesHolder {

    private static List<String> fileNames = new ArrayList<>();

    public static void addFileName(String fileName) {
        fileNames.add(fileName);
    }

    public static String getFileNames() {
        StringBuilder stringBuilder = new StringBuilder();
        fileNames.forEach(fileName -> {
            stringBuilder.append(fileName).append("\n");
        });

        return stringBuilder.toString();
    }
}
