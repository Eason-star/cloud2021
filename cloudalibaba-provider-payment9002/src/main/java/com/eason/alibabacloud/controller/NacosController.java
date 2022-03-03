package com.eason.alibabacloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NacosController {
    @Value("${server.port}")
    private String port;
    @GetMapping("/nacos/{id}")
    public String getPort(@PathVariable("id") String id){
        return "NACOS "+port+'\t'+id;
    }
}
