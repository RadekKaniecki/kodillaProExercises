package com.kodilla.integrationexercise;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.Pollers;
import org.springframework.integration.file.FileReadingMessageSource;
import org.springframework.integration.file.FileWritingMessageHandler;
import org.springframework.integration.file.support.FileExistsMode;

import java.io.File;

@Configuration
public class FileIntegrationConfiguration {

    private final String INPUT_DIRECTORY = "data/input";
    private final String OUTPUT_DIRECTORY = "data/output";

    @Bean
    IntegrationFlow integrationFlow(FileReadingMessageSource readingMessageSource,
                                    FileTransformer fileTransformer,
                                    FileWritingMessageHandler writingMessageHandler) {
        return IntegrationFlows.from(readingMessageSource, config -> config.poller(Pollers.fixedDelay(100L)))
                .transform(fileTransformer, "getFileNames")
                .handle(writingMessageHandler)
                .get();
    }

    @Bean
    CustomFileNameGenerator fileNameGenerator() {
        return new CustomFileNameGenerator();
    }

    @Bean
    FileReadingMessageSource fileReadingMessageSource() {
        FileReadingMessageSource messageSource = new FileReadingMessageSource();
        messageSource.setDirectory(new File(INPUT_DIRECTORY));

        return messageSource;
    }

    @Bean
    FileWritingMessageHandler fileWritingMessageHandler() {
        File outputDirectory = new File(OUTPUT_DIRECTORY);
        FileWritingMessageHandler writingMessageHandler = new FileWritingMessageHandler(outputDirectory);
        writingMessageHandler.setFileNameGenerator(fileNameGenerator());
        writingMessageHandler.setExpectReply(false);
        writingMessageHandler.setFileExistsMode(FileExistsMode.APPEND);

        return writingMessageHandler;
    }

    @Bean
    FileTransformer fileTransformer() {
        return new FileTransformer();
    }
}
