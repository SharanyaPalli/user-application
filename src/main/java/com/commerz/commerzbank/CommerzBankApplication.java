package com.commerz.commerzbank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CommerzBankApplication {

    public static void main(String[] args) {
        SpringApplication.run(CommerzBankApplication.class, args);
    }

}
