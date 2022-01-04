package com.eason.springcloud.service;

import com.eason.springcloud.po.ElementValueDTO;
import com.eason.springcloud.service.impl.PaymentHystrixServiceimpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
@FeignClient(value = "ELEMENT-SERVER2")
public interface ElementService {
    @PostMapping("/elementValueFeign/getByEleCodeAndWhereMap")
    public List<ElementValueDTO> getByWhereObjectMap(
            @RequestParam("eleCode") String eleCode,
            @RequestParam("tenant_id") long tenant_id,
            @RequestParam("busiyear") long busiyear
            );

}
