package com.example.user.mfa;

import com.example.user.user.EmailService;
import com.warrenstrange.googleauth.GoogleAuthenticator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Slf4j
@Service
public class MfaService {

    private final GoogleAuthenticator googleAuthenticator;

    @Autowired
    private JavaMailSender mailSender;
    private final EmailService emailService;

    public MfaService(EmailService emailService) {
        this.emailService = emailService;
        this.googleAuthenticator = new GoogleAuthenticator();
    }

    public String generateSecretKey(String username, String email) {
        String secretKey = googleAuthenticator.createCredentials().getKey();
        log.info("Generated MFA Secret Key for {}: {}", username, secretKey);

        // Envoyer la clé MFA par email
        String message = "Votre clé secrète MFA : " + secretKey + "\nAjoutez cette clé dans Google Authenticator.";
        emailService.sendEmail(email, "Votre clé MFA", message);

        return secretKey;
    }

    public String getQrCodeUrl(String username, String secret) {
        String issuer = "Maghrebia";  // Nom de l'application
        String encodedIssuer = URLEncoder.encode(issuer, StandardCharsets.UTF_8);
        String encodedUsername = URLEncoder.encode(username, StandardCharsets.UTF_8);

        return String.format("otpauth://totp/%s:%s?secret=%s&issuer=%s",
                encodedIssuer, encodedUsername, secret, encodedIssuer);
    }

    public boolean verifyCode(String secret, int code) {
        boolean isValid = googleAuthenticator.authorize(secret, code);
        log.info("MFA Code verification for {}: {}", secret, isValid ? "SUCCESS" : "FAILED");
        return isValid;
    }
}
