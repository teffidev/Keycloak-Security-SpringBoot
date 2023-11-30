package com.example.msusers.controller;

import com.example.msusers.domain.User;
import com.example.msusers.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/all")
    public ResponseEntity<?> getUsers() {
        return new ResponseEntity<>(userService.getUsers(), HttpStatus.ACCEPTED);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserWithBills(@PathVariable("userId") String userId) {
        User user = userService.getBillsByUserId(userId);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}