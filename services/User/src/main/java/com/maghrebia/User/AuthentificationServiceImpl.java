package com.maghrebia.User;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthentificationServiceImpl implements AuthentificationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;  // ✅ Injection correcte

    public User signup(SignUpRequest signUpRequest) {
        User user = new User();
        user.setCin(signUpRequest.getCin());
        user.setNom(signUpRequest.getNom());
        user.setPrenom(signUpRequest.getPrenom());
        user.setAdresse(signUpRequest.getAdresse());
        user.setEmail(signUpRequest.getEmail());
        user.setRole(ERole.AGENT);
        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));

        return userRepository.save(user);
    }
}
