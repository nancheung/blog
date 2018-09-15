package com.blog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author NanCheung
 */
@SpringBootApplication
@EnableScheduling
public class BlogApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(BlogApplication.class, args);
    }
}
