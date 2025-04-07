package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Import;
import com.example.config.AppConfig;

/**
 * This is a class that is used to run your application.
 *
 * DO NOT CHANGE ANYTHING IN THIS CLASS
 *
 */
@SpringBootApplication
@Import(AppConfig.class)
@EntityScan("com.example.entity")
public class SocialMediaApp {
    /**
     * Runs the application
     * @param args The arguments of the program.
     * @throws InterruptedException 
     */
    public static void main(String[] args) throws InterruptedException {
        SpringApplication.run(SocialMediaApp.class, args);
    }
}
