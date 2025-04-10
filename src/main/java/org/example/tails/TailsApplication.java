package org.example.tails;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication

public class TailsApplication {

    public static void main(String[] args) {
        SpringApplication.run(TailsApplication.class, args);
    }

}
