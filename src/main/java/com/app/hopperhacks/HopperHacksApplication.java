package com.app.hopperhacks;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class HopperHacksApplication {

    public static void main(String[] args) {
        SpringApplication.run(HopperHacksApplication.class, args);
    }

}
