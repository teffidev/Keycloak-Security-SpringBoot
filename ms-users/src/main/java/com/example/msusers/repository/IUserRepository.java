package com.example.msusers.repository;

import com.example.msusers.domain.User;

import java.util.List;

public interface IUserRepository {

    public User findById(String Id);
    List<User> getUsers();
}
