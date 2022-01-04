package com.eason.springcloud.controller;

import com.eason.springcloud.po.CommonResult;
import com.eason.springcloud.po.Payment;
import com.eason.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/payment")
public class PaymentController {
    @Resource
    private PaymentService paymentService;
    @Value("${server.port}")
    private String serverPort;
    @Resource
    private DiscoveryClient discoveryClient;

    @PostMapping("/insert")
    public CommonResult creatPayment(@RequestBody Payment payment){
        int count = paymentService.create(payment);
        log.info("数据插入======"+count);
        if (count>0){
            return new CommonResult(200,"插入数据成功 port:"+serverPort,count);
        }else {
            return new CommonResult(400,"插入数据失败",null);
        }
    }

    @GetMapping("/getpayment/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long  id){
        Payment payment = paymentService.getPaymentById(id);
        if (payment!=null){
            log.info("查询成功---------"+payment);
            return new CommonResult(200,"查询数据成功 port:"+serverPort,payment);
        }else {
            log.info("查询失败"+payment);
            return new CommonResult(400,"查询数据失败",null);
        }
    }

    @GetMapping("/discovery")
    public DiscoveryClient getDiscoveryClient(){
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for (ServiceInstance instance : instances) {
            log.info(instance.getInstanceId()+"\t"
                    +instance.getHost()+"\t"+instance.getUri()+"\t"+instance.getPort());
        }
        return this.discoveryClient;
    }

    @GetMapping(value = "/test/timeout")
    public String timeout(){
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return serverPort;
    }

    @GetMapping(value="/port")
    public String getServerPort(){
        return serverPort;
    }
}
