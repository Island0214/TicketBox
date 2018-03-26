package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableConfigurationProperties
@EnableScheduling
@EnableJpaRepositories
@ComponentScan(basePackages = { "com.example"})
public class TicketBoxServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(TicketBoxServerApplication.class, args);
    }
}
