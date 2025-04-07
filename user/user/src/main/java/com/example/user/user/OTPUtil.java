package com.example.user.user;


import com.eatthepath.otp.TimeBasedOneTimePasswordGenerator;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;
import java.time.Duration;
import java.time.Instant;
import java.util.Base64;

public class OTPUtil {
    private static final int TIME_STEP = 30; // Durée de validité (secondes)

    // Générer une clé secrète
    public static String generateSecretKey() throws NoSuchAlgorithmException {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("HmacSHA1");
        keyGenerator.init(160); // Google Authenticator utilise 160 bits
        SecretKey secretKey = keyGenerator.generateKey();
        return Base64.getEncoder().encodeToString(secretKey.getEncoded());
    }

    // Générer un OTP basé sur la clé secrète
    public static String generateOTP(String base32Secret) throws Exception {
        byte[] keyBytes = Base64.getDecoder().decode(base32Secret);
        SecretKey secretKey = new javax.crypto.spec.SecretKeySpec(keyBytes, "HmacSHA1");

        TimeBasedOneTimePasswordGenerator totp = new TimeBasedOneTimePasswordGenerator(
                Duration.ofSeconds(TIME_STEP) // Correction ici
        );

        Instant now = Instant.now();
        return String.valueOf(totp.generateOneTimePassword(secretKey, now));
    }

    // Vérifier si l'OTP fourni par l'utilisateur est valide
    public static boolean verifyOTP(String secretKey, String userOTP) throws Exception {
        String generatedOTP = generateOTP(secretKey);
        return generatedOTP.equals(userOTP);
    }

    public static void main(String[] args) throws Exception {
        // Générer une clé secrète pour l'utilisateur
        String secretKey = generateSecretKey();
        System.out.println("Clé Secrète: " + secretKey);

        // Générer un OTP
        String otpCode = generateOTP(secretKey);
        System.out.println("Code OTP généré: " + otpCode);

        // Simuler la vérification d'un code OTP saisi par l'utilisateur
        String userOTP = otpCode; // Supposons que l'utilisateur a saisi ce code
        boolean isValid = verifyOTP(secretKey, userOTP);
        System.out.println("Vérification OTP: " + (isValid ? "Valide ✅" : "Invalide ❌"));
    }
}
