package com.maghrebia.User;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200") // Angular port

public class AuthentificationController {
    private final AuthentificationService authentificationService;
    private final  UserRepository userRepository;

    @PostMapping("/singup")
    public ResponseEntity<User> signup(@RequestBody SignUpRequest signUpRequest){
        return ResponseEntity.ok(authentificationService.signup(signUpRequest));
    }

    @PostMapping("/singin")
    public ResponseEntity<jwtAuthentifactionResponse> signin(@RequestBody SignInRequest signinRequest){
        return ResponseEntity.ok(authentificationService.signin(signinRequest));
    }
    @PostMapping("/refresh")
    public ResponseEntity<jwtAuthentifactionResponse> refresh(@RequestBody RefreshTokenRequest refreshTokenRequest){
        return ResponseEntity.ok(authentificationService.refreshToken(refreshTokenRequest));
    }
    @GetMapping("/email/{email}")
    public ResponseEntity<User> getUserByEmail(@PathVariable String email) {
        Optional<User> user = userRepository.findByEmail(email);
        return user.map(ResponseEntity::ok)
                .orElseThrow(() -> new RuntimeException("User not found with email: " + email));
    }
}
