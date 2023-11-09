package com.example.alidemoorder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class AliDemoOrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(AliDemoOrderApplication.class, args);
    }

}
