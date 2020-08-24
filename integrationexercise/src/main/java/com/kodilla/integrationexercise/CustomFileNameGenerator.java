package com.kodilla.integrationexercise;

import org.springframework.integration.file.FileNameGenerator;
import org.springframework.messaging.Message;

public class CustomFileNameGenerator implements FileNameGenerator {

    @Override
    public String generateFileName(Message<?> message) {
        return "fileNames";
    }
}
