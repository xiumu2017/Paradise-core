package com.paradise.core.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Paradise
 */
@SpringBootApplication(scanBasePackages = "com.paradise.core")
public class ParadiseAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(ParadiseAppApplication.class, args);
    }

}
