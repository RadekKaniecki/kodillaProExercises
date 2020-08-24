package com.kodilla.beanlifecycleexercise;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfiguration {

    @Bean
    LibraryManger libraryManger() {
        return new LibraryManger();
    }

    @Bean
    BeanMonitor beanMonitor() {
        return new BeanMonitor();
    }
}
