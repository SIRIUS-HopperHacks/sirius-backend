package com.app.sirius;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class SiriusApplication {

    public static void main(String[] args) {
        SpringApplication.run(SiriusApplication.class, args);
    }

}
