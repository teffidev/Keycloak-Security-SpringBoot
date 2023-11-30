package com.example.msusers.repository;

import com.example.msusers.configuration.feign.FeignInterceptor;
import com.example.msusers.domain.Bill;
import com.example.msusers.loadBalancer.LoadBalancerUser;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "ms-bill", configuration = FeignInterceptor.class)
public interface FeignBillRepository {

    @GetMapping("/bills/user")
    List<Bill> getBillsByUserId(@RequestParam("userId") String userId);
}
