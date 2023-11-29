package com.example.msusers.service;

import com.example.msusers.domain.Bill;
import com.example.msusers.domain.User;
import com.example.msusers.repository.FeignBillRepository;
import com.example.msusers.repository.IUserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final IUserRepository userRepository;
    private final FeignBillRepository feignBillRepository;

    public UserService(IUserRepository userRepository, FeignBillRepository feignBillRepository) {
        this.userRepository = userRepository;
        this.feignBillRepository = feignBillRepository;
    }

    public User findById(String Id) {
        return userRepository.findById(Id);
    }

    public List<User> getUsers() {
        return userRepository.getUsers();
    }

    public User getBillsByUserId(String Id) {
        User user = userRepository.findById(Id);
        ResponseEntity<List<Bill>> bills = feignBillRepository.getBillsByUserId(Id);
        user.setBills(bills.getBody());

        return user;
    }
}
