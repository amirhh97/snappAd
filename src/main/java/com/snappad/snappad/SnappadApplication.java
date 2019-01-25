package com.snappad.snappad;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import javax.servlet.annotation.WebServlet;
import java.util.Arrays;
import java.util.Collections;

@SpringBootApplication
@ComponentScan("com.snappad")
@EnableJpaRepositories("com.snappad.dao")
@EntityScan("com.snappad.model")
@ServletComponentScan("com.snappad")
public class SnappadApplication {
    public static void main(String[] args) {
        SpringApplication.run(SnappadApplication.class, args);
    }
}
