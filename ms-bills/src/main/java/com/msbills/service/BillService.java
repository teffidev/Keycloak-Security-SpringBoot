package com.msbills.service;

import com.msbills.models.Bill;
import com.msbills.repositories.BillRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BillService {

    private final BillRepository repository;

    public List<Bill> getAllBill() {

        return repository.findAll();
    }

    public void createBill(Bill bill) {
        repository.save(bill);
    }

    public List<Bill> getBillsByIdUser(String idUser) {
        return repository.getBillsByIdUser(idUser);
    }

}
