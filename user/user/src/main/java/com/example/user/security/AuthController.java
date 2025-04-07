package com.example.user.security;

import com.example.user.mfa.MfaService;
import com.example.user.user.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private GoogleAuthService googleAuthService;

    @Autowired
    private EmailService emailService;

    @GetMapping("/generate-secret")
    public String generateSecret(@RequestParam String email) {
        try {
            logger.info("Generating secret for email: {}", email);

            // Générer un code secret
            String secret = googleAuthService.generateSecretKey();
            logger.info("Secret generated: {}", secret);

            // Envoyer le code secret par e-mail
            emailService.sendEmail(email, "Your Google Authenticator Code", "Your secret code is: " + secret);
            logger.info("Email sent successfully to: {}", email);

            return "Secret generated and sent to email: " + email;
        } catch (Exception e) {
            logger.error("Error in generateSecret endpoint", e);
            throw new RuntimeException("Failed to generate secret and send email", e);
        }
    }
    private final AuthService authService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private final MfaService mfaService;
    private final UserRepository userRepository;
    @Autowired
    private UserService userService;



    @PostMapping("/verify-code")
    public String verifyCode(@RequestParam String secret, @RequestParam int code) {
        boolean isValid = googleAuthService.verifyCode(secret, code);
        if (isValid) {
            return "Code is valid!";
        } else {
            return "Invalid code.";
        }
    }

    @Autowired
    public AuthController(AuthService authService, AuthenticationManager authenticationManager, JwtUtils jwtUtils, MfaService mfaService, UserRepository userRepository) {
        this.authService = authService;
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
        this.mfaService = mfaService;
        this.userRepository = userRepository;
    }


    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody UserDto userDto) {
        try {
            // Enregistrer l'utilisateur
            authService.registerUser(userDto);

            // Générer la clé secrète MFA et l’envoyer par email
            String secret = mfaService.generateSecretKey(userDto.getUsername(), userDto.getEmail());

            // Stocker la clé MFA en base
            authService.saveMfaSecret(userDto.getUsername(), secret);

            return ResponseEntity.ok("User registered successfully! Check your email for MFA setup.");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
        try {
            System.out.println("Tentative de connexion pour : " + loginRequest.getUsername());

            // Authenticate user using username and password
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
            );

            System.out.println("Authentification réussie pour : " + loginRequest.getUsername());

            SecurityContextHolder.getContext().setAuthentication(authentication);

            // After password authentication, ask for MFA token
            // Retrieve the stored secret from the database
            String storedSecret = authService.getMfaSecret(loginRequest.getUsername());

            if (storedSecret != null) {
                // Convert the MFA code (String) to an integer
                int mfaCode;
                try {
                    mfaCode = Integer.parseInt(loginRequest.getMfaCode());
                } catch (NumberFormatException e) {
                    return ResponseEntity.status(400).body("Invalid MFA code format");
                }

                // Check the MFA token entered by the user
                boolean isMfaValid = mfaService.verifyCode(storedSecret, mfaCode);

                if (!isMfaValid) {
                    return ResponseEntity.status(401).body("MFA token is invalid");
                }
            }

            // If both username/password and MFA token are valid, generate a JWT token
            String jwt = jwtUtils.generateJwtToken(loginRequest.getUsername());

            return ResponseEntity.ok(new JwtResponse(jwt));
        } catch (Exception e) {
            System.err.println("Erreur lors de l'authentification : " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(401).body("Échec de l'authentification : " + e.getMessage());
        }
    }
    // Endpoint pour demander la réinitialisation
    @PostMapping("/forgot-password")
    public ResponseEntity<String> forgotPassword(@RequestParam String email) {
        userService.forgotPassword(email);
        return ResponseEntity.ok("Email envoyé avec succès !");
    }

    // Endpoint pour réinitialiser le mot de passe
    @PostMapping("/reset-password")
    public ResponseEntity<String> resetPassword(@RequestParam String token, @RequestParam String newPassword) {
        userService.resetPassword(token, newPassword);
        return ResponseEntity.ok("Mot de passe mis à jour avec succès !");
    }



}
