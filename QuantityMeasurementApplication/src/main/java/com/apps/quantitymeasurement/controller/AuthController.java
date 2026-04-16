package com.apps.quantitymeasurement.controller;

import com.apps.quantitymeasurement.entity.User;
import com.apps.quantitymeasurement.service.AuthService;
import com.apps.quantitymeasurement.util.JwtUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final JwtUtil jwtUtil;
    private final AuthService authService;

    public AuthController(JwtUtil jwtUtil, AuthService authService) {
        this.jwtUtil = jwtUtil;
        this.authService = authService;
    }

    // REGISTER
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User user) {
        authService.register(user);
        return new ResponseEntity<>("User Registered Successfully",HttpStatus.OK);
    }

    // LOGIN
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user) {

        User validUser = authService.login(user.getUsername(), user.getPassword());
        String token = jwtUtil.generateToken(validUser.getUsername());
        return new ResponseEntity<>(token,HttpStatus.OK);
    }
}
