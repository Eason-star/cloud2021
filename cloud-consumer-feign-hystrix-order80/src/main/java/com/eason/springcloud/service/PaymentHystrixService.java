package com.eason.springcloud.service;

import com.eason.springcloud.service.impl.PaymentHystrixServiceimpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Service
@FeignClient(value = "cloud-provider-hystrix-payment",fallback = PaymentHystrixServiceimpl.class)
public interface PaymentHystrixService {

@GetMapping("/payment/hystrix/ok/{id}")
String paymentInfo_OK(@PathVariable("id") Integer id);

@GetMapping("/payment/hystrix/timeout/{id}")
String paymentInfo_TimeOut(@PathVariable("id") Integer id);
}
