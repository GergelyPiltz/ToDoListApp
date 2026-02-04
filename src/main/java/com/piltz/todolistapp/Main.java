package com.piltz.todolistapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        System.out.println(System.getProperty("java.version"));
        SpringApplication.run(Main.class, args);
        TaskApp.run();
    }
}
