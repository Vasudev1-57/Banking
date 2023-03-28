package com.bankproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication

public class BankprojectApplication {

    public static void main(String[] args) {
        SpringApplication.run(BankprojectApplication.class, args);

    }

//    @GetMapping("/")
//    public String name() {
//         return "vasudev1";
//    }
}
