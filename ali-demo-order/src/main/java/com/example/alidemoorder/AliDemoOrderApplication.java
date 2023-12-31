package com.example.alidemoorder;

import io.seata.spring.annotation.datasource.EnableAutoDataSourceProxy;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableFeignClients
@EnableAutoDataSourceProxy
@ComponentScan("com.example.alidemoorder.*")
public class AliDemoOrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(AliDemoOrderApplication.class, args);
    }

}
