package com.eason.springcloud.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LogConfigration {

    @Bean
    Logger.Level feginLog(){
        return Logger.Level.FULL;
    }
}
