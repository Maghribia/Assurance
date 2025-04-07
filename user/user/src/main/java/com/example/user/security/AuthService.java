package com.example.user.security;

import com.example.user.user.User;
import com.example.user.user.UserDto;
import com.example.user.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void registerUser(UserDto userDto) {
        if (userRepository.existsByUsername(userDto.getUsername())) {
            throw new RuntimeException("Username is already taken!");
        }

        if (userRepository.existsByEmail(userDto.getEmail())) {
            throw new RuntimeException("Email is already in use!");
        }

        User user = new User(
                userDto.getUsername(),
                userDto.getEmail(),
                passwordEncoder.encode(userDto.getPassword()) // Hacher le mot de passe
        );

        userRepository.save(user);
    }
    public void updatePasswordsToBcrypt() {
        List<User> users = userRepository.findAll();
        for (User user : users) {
            String hashedPassword = passwordEncoder.encode(user.getPassword());
            user.setPassword(hashedPassword);
            userRepository.save(user);
        }
    }
    // Method to save the MFA secret for a user
    public void saveMfaSecret(String username, String secret) {
        User user = userRepository.findByUsername(username);
        if (user != null) {
            user.setMfaSecret(secret); // Make sure User class has this method
            userRepository.save(user); // Save the user with the updated MFA secret
        } else {
            throw new RuntimeException("User not found!");
        }
    }

    // Method to retrieve the MFA secret for a given username
    public String getMfaSecret(String username) {
        User user = userRepository.findByUsername(username);
        if (user != null) {
            return user.getMfaSecret(); // Assuming 'getMfaSecret' exists in the User class
        } else {
            throw new RuntimeException("User not found!");
        }
    }
}
