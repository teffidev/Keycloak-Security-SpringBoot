package com.example.msusers.repository;

import com.example.msusers.domain.Bill;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BillRepository {

    private FeignBillRepository feignBillRepository;

    public List<Bill> findByUserId(String idUser) {
        ResponseEntity<List<Bill>> response = feignBillRepository.findByUserId(idUser);
        return response.getBody();
    }

}
