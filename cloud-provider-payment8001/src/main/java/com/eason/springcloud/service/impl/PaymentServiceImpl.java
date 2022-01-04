package com.eason.springcloud.service.impl;

import com.eason.springcloud.dao.PaymentDao;
import com.eason.springcloud.po.Payment;
import com.eason.springcloud.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    private PaymentDao paymentDao;
    @Override
    public int create(Payment payment) {
        int i = paymentDao.create(payment);
        return i;
    }

    @Override
    public Payment getPaymentById(Long id) {
        Payment paymentById = paymentDao.getPaymentById(id);
        return paymentById;
    }
}
