package com.eason.springcloud.controller;

import com.eason.springcloud.po.CommonResult;
import com.eason.springcloud.po.Payment;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.Map;

@RestController
@RequestMapping("/consumer")
public class ConsumerController {

    public final String url="http://cloud-payment-service/payment";
    public final String ctj="http://localhost:19090/restapi/4a/user";
    @Resource
    private RestTemplate restTemplate;

    @GetMapping("/insert")
    public CommonResult<Payment> creatPayment(Payment payment){
        return restTemplate.postForObject(url+"/insert",payment,CommonResult.class);
    }

    @GetMapping("/getpayment/{id}")
    public CommonResult<Payment> getPayment(@PathVariable("id") Long id){
        return restTemplate.getForObject(url+"/getpayment/"+id,CommonResult.class,id);
    }

    @GetMapping("/getpaymentForEntity/{id}")
    public CommonResult<Payment> getPayment2(@PathVariable("id") Long id){
        ResponseEntity<CommonResult> forEntity = restTemplate.getForEntity
                (url + "/getpayment/" + id, CommonResult.class, id);

        if (forEntity.getStatusCode().is2xxSuccessful()){
            return forEntity.getBody();
        }else {
            return new CommonResult<>(400,"操作失败");
        }
    }

    @GetMapping("/page/users/{tenantId}/{orgTypeId}/{orgId}/{page}/{rows}")
    public CommonResult<Payment> getUser(@RequestBody(required = false) Map<String,Object> param,
                                      @PathVariable(value = "tenantId") Long tenantId,
                                      @PathVariable(value = "orgTypeId") String orgTypeId,
                                      @PathVariable(value = "orgId") String orgId,
                                      @PathVariable(value = "page") int page,
                                      @PathVariable(value = "rows") int rows){
        return restTemplate.getForObject(ctj+"/page/users/"+tenantId+"/"+orgTypeId+"/"
                        +orgId+"/"+orgId+"/"+page+"/"+rows,
                CommonResult.class,tenantId,orgTypeId,orgId,page,rows);
    }
}
