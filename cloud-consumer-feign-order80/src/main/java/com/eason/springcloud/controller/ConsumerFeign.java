package com.eason.springcloud.controller;

import com.eason.springcloud.po.CommonResult;
import com.eason.springcloud.po.Payment;
import com.eason.springcloud.service.PaymentFeignService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class ConsumerFeign {
    @Resource
    private PaymentFeignService paymentFeignService;

    @GetMapping(value = "/consumer/payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id) {
        CommonResult paymentById = paymentFeignService.getPaymentById(id);
        return paymentById;
    }
    /**
     * @Description: Fegin 超时实验
     * @Author: Eason
     * @Date: 2021/12/9 下午3:11
     * @return: java.lang.String
     **/
    @GetMapping(value = "/consumer/payment/feign/timeout")
    public String paymentFeignTimeout() {
        // OpenFeign客户端一般默认等待1秒钟
        return paymentFeignService.timeout();
    }
}
