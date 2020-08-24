package com.kodilla.integrationexercise;

import org.springframework.messaging.Message;

import java.io.File;

public class FileTransformer {

    private static final String HEADER_FILE_NAME = "file_name";

    public String getFileNames(Message<File> message) {
        return message.getHeaders().get(HEADER_FILE_NAME) + "\n";
    }
}
