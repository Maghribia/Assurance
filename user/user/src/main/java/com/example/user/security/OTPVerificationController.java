package com.example.user.security;

import com.example.user.user.UserService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// Exemple de la vérification de l'OTP
@RestController
@RequestMapping("/api/authh")
public class OTPVerificationController {

    private final UserService userService;

    public OTPVerificationController(UserService userService) {
        this.userService = userService;
    }


}
