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
@LoadBalancerClient(name = "ms-bill", configuration = LoadBalancerUser.class)
public interface FeignBillRepository {

    @RequestMapping(method = RequestMethod.GET, value = "/bills/findByUserId")
    ResponseEntity<List<Bill>> findByUserId(@RequestParam String idUser);

    @GetMapping("/bills/user/{userId}")
    ResponseEntity<List<Bill>> getBillsByUserId(@PathVariable String userId);
}
