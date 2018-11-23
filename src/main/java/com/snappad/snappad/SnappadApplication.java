package com.snappad.snappad;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan("com.snappad.controller")
@EnableJpaRepositories("com.snappad.dao")
@EntityScan("com.snappad.model")
public class SnappadApplication {
    public static void main(String[] args) {
        SpringApplication.run(SnappadApplication.class, args);
    }
}
