package org.example;

import io.seata.spring.annotation.datasource.EnableAutoDataSourceProxy;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@EnableAutoDataSourceProxy //开启数据源代理
public class AliDemoStorageApplication {

    public static void main(String[] args) {
        SpringApplication.run(AliDemoStorageApplication.class, args);
    }

}
