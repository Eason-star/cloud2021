package com.eason.springcloud.service.impl;

import com.eason.springcloud.service.PaymentHystrixService;
import org.springframework.stereotype.Service;

@Service
public class PaymentHystrixServiceimpl implements PaymentHystrixService {
    @Override
    public String paymentInfo_OK(Integer id) {
        return "服务调用失败!!!";
    }

    @Override
    public String paymentInfo_TimeOut(Integer id) {
        return "服务调用失败!!!";
    }
}
