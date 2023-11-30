package com.example.msusers.service;

import com.example.msusers.domain.Bill;
import com.example.msusers.domain.User;
import com.example.msusers.repository.FeignBillRepository;
import com.example.msusers.repository.IUserRepository;
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

    public List<User> getUsers() {
        return userRepository.getUsers();
    }

    public User getBillsByUserId(String id) {

        User user = null;
        try {
            user = userRepository.findById(id);
            List<Bill> bills = feignBillRepository.getBillsByUserId(id);
            if (user.getId() != null) {
                user.setBills(bills);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return user;
    }
}
