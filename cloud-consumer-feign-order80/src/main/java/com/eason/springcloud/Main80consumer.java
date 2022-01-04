package com.eason.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class Main80consumer {
    public static void main(String[] args) {
        SpringApplication.run(Main80consumer.class,args);
    }
}
