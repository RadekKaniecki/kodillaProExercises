package com.kodilla.beanlifecycleexercise;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class BeanLifecycleExerciseApplication {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(AppConfiguration.class);
        context.refresh();

        LibraryManger libraryManger = context.getBean(LibraryManger.class);
        System.out.println("Context and beans are set up and ready to work");
        context.close();
    }
}
