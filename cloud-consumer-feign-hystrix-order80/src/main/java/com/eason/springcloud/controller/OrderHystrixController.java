package com.eason.springcloud.controller;

import com.eason.springcloud.service.PaymentHystrixService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
//@DefaultProperties(defaultFallback = "paymentInfo_TimeOut_Global")
@DefaultProperties(defaultFallback = "payment_Global_FallbackMethod")
public class OrderHystrixController {

    @Resource
    private PaymentHystrixService paymentHystrixService;


    @GetMapping("/consumer/payment/hystrix/ok/{id}")
    public String paymentInfo_OK(@PathVariable("id") Integer id)   {
        String result = paymentHystrixService.paymentInfo_OK(id);
        return result;
    }

    @GetMapping("/consumer/payment/hystrix/timeout/{id}")
    @HystrixCommand(fallbackMethod = "paymentInfo_TimeOut_OK",commandProperties =
            {@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value="1000" )})
   // @HystrixCommand
    public String paymentInfo_TimeOut(@PathVariable("id") Integer id) {
        //int age = 10/0;
            try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String result = paymentHystrixService.paymentInfo_TimeOut(id);
        return result;
    }

    public String paymentInfo_TimeOut_OK(@PathVariable("id") Integer id) {
        String result = "80端超时，请稍后重试！！！"+id;
        return result;
    }

    public String payment_Global_FallbackMethod() {
        String result = "80端超时，请稍后重试===Global！！！";
        return result;
    }

}
