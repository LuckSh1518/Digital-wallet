package com.tyagi.fintech1.controller;

import com.tyagi.fintech1.entity.User;
import com.tyagi.fintech1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestParam String name,
                                              @RequestParam String email,
                                              @RequestParam String mobile,
                                              @RequestParam String password) {
        User user = userService.registerUser(name, email, mobile, password);
        return ResponseEntity.ok(user);
    }

    @GetMapping
    public ResponseEntity<User> getUser(@RequestParam String email) {
        User user = userService.findByEmail(email);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/by-mobile")
    public ResponseEntity<User> getUserByMobile(@RequestParam String mobile) {
        return ResponseEntity.ok(userService.findByMobile(mobile));
    }
}