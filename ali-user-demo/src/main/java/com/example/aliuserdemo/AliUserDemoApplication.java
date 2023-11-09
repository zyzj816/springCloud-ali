package com.example.aliuserdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableFeignClients
@ComponentScan("com.example.aliuserdemo.*")
public class AliUserDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(AliUserDemoApplication.class, args);
    }

}
