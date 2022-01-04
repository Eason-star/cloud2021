package com.eason.springcloud.service;

import com.eason.springcloud.po.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Service
@FeignClient(value = "CLOUD-PAYMENT-SERVICE")
public interface PaymentFeignService {
    @GetMapping("/payment/getpayment/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long  id);

    /**
     * @Description:Fegin超时实验
     * @Author: Eason
     * @Date: 2021/12/9 下午3:10
     * @return: java.lang.String
     **/
    @GetMapping(value = "/payment/test/timeout")
    public String timeout();
}
